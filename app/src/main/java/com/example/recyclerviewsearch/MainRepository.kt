package com.example.recyclerviewsearch

import android.util.Log
import com.example.recyclerviewsearch.model.data.Color
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import java.io.InputStream

class MainRepository(private val colors: InputStream) {

    suspend fun prepareData():List<Color> {
        val colorsList = mutableListOf<Color>()
        csvReader().openAsync(colors) {
            readAllAsSequence().asFlow().collect {
                colorsList.add(
                    Color(
                        it[0],
                        it[1],
                        it[2],
                        it[3].toInt(),
                        it[4].toInt(),
                        it[5].toInt()
                    )
                )
            }
        }
        return colorsList
    }

}