package com.example.musicplayer.core.service

import android.content.ContentUris
import android.content.Context
import android.media.MediaPlayer
import android.provider.MediaStore
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import com.example.musicplayer.core.model.Song

class MusicService(private val context: Context) {

    private val _mediaPlayer = mutableStateOf<MediaPlayer?>(value = null)
//    val mediaPlayer: State<MediaPlayer?> = _mediaPlayer

    private val _songs = mutableStateOf<List<Song>>(emptyList())
    val song: State<List<Song>> = _songs

    private val _currentSong = mutableStateOf<Song?>(value = null)
    val currentSong: State<Song?> = _currentSong

    private val _indexSong = mutableIntStateOf(value = -1)

    init{
        val songs = mutableListOf<Song>()
        //projection is what column/field of MediaStore you need
        val projection = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.TITLE
        )
        //selection is to filtering the audio
        val selection = "${MediaStore.Audio.Media.DURATION} >= ?"

        //selectionArgs is to specify the filtering
        val selectionArgs = arrayOf("90000")

        //sort order is to give the order of the data inside content provider
        //asc(ascending) means the songs will begin alphabetically
        val sortOrder = "${MediaStore.Audio.Media.TITLE} ASC"

        context.contentResolver.query(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            projection,
            selection,
            selectionArgs,
            sortOrder
        )?.use { cursor ->
            val idColumn = cursor.getColumnIndex(projection[0])
            val nameColumn = cursor.getColumnIndex(projection[1])
            //iterate through cursors
            while(cursor.moveToNext()){
                val id = cursor.getLong(idColumn)
                val name = cursor.getString(nameColumn)
                val uri = ContentUris.withAppendedId(
                    MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                    id
                )
                songs.add(
                    Song(
                        id = id,
                        name = name,
                        uri = uri
                    )
                )
            }
        }
        _songs.value = songs
    }

    fun playSong(songId: Long){
        stopSong()

        _songs.value.find {
            it.id == songId
        }?.let { song ->
            //change the currentSong
            _currentSong.value = song

            //update the currentSong isPlaying
            song.isPlaying.value = true

            //update the indexSong
            _indexSong.intValue = _songs.value.indexOf(song)

            song.uri?.let { songUri ->
                _mediaPlayer.value = MediaPlayer().apply {
                    setDataSource(context, songUri)
                    prepare()
                    start()
                    //this is to track if the playback is done
                    setOnCompletionListener {
                        _currentSong.value?.isPlaying?.value = false
                        Log.d("MediaPlayer", "Playback completed for ${song.name}")
                    }
                }

            }
        }

    }

    private fun stopSong(){
        //this is to update all the songs isPlaying state
        _songs.value.find { song ->
            song.isPlaying.value
        }?.let {
            it.isPlaying.value = false
        }
        _mediaPlayer.value?.stop()
        _mediaPlayer.value?.release()
        _mediaPlayer.value = null
    }

    fun pauseSong(){
        _mediaPlayer.value?.let{
            if(it.isPlaying){
                it.pause()
                _currentSong.value?.isPlaying?.value = false
            }
        }
    }

    fun resumeSong(){
        _mediaPlayer.value?.start()
        _currentSong.value?.isPlaying?.value = true
    }

    fun moveSong(forward: Boolean){
        val size = _songs.value.size
        val index = when(forward){
            true -> (_indexSong.intValue + 1) % size
            false -> if(_indexSong.intValue == 0) size - 1 else _indexSong.intValue - 1
        }
        val songId = _songs.value[index].id
        playSong(songId)
    }

}