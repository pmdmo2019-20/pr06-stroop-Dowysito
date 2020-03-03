package es.iessaladillo.pedrojoya.stroop.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import es.iessaladillo.pedrojoya.stroop.R
import java.util.concurrent.Executors


@Database(entities = [Player::class,Game::class], version = 1)
abstract class GameDatabase : RoomDatabase() {

    abstract val dao: DatabaseDao

    companion object {

        @Volatile
        private var INSTANCE: GameDatabase? = null

        fun getInstance(context: Context): GameDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            GameDatabase::class.java,
                            "game_database"
                        ).build()
                    }
                }
            }
            return INSTANCE!!
        }
    }
}