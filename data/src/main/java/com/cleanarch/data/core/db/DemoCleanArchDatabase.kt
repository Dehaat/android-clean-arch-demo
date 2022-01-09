package com.cleanarch.data.core.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.cleanarch.data.book.local.database.db.BookDao
import com.cleanarch.data.book.local.database.db.Converters
import com.cleanarch.data.book.local.database.models.BookEntity

@Database(entities = [BookEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class DemoCleanArchDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao

    companion object {
        @Volatile
        private var INSTANCE: DemoCleanArchDatabase? = null

        fun getDatabase(appContext: Context): DemoCleanArchDatabase {
            val tempInstance =
                INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    appContext, DemoCleanArchDatabase::class.java,
                    DemoCleanArchDatabase::class.simpleName!!
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}