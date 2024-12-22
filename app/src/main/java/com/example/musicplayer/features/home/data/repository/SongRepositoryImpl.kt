package com.example.musicplayer.features.home.data.repository

import android.content.ContentResolver
import android.content.ContentUris
import android.provider.MediaStore
import com.example.musicplayer.core.model.Song
import com.example.musicplayer.features.home.domain.repository.SongRepository

class SongRepositoryImpl(private val contentResolver: ContentResolver): SongRepository {
    override fun getSongs(): List<Song> {

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

        contentResolver.query(
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

        return songs

    }
}