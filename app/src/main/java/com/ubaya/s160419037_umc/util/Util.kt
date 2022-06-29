package com.ubaya.s160419037_umc.util

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.ubaya.s160419037_umc.R
import com.ubaya.s160419037_umc.model.UmcDatabase
import java.lang.Exception
import java.util.concurrent.Executors

@BindingAdapter("android:imageUrl", "android:progressBar")
fun loadImageFromURL(view: ImageView, url: String?, progressBar: ProgressBar) {
    if (url != null) {
        if (url!= "none") {
            view.loadImage(url!!, progressBar)
        }
        else {
            progressBar.visibility = View.GONE
            view.visibility = View.GONE
        }
    }
}

fun ImageView.loadImage(url: String?, progressBar: ProgressBar){
    Picasso.get()
        .load(url)
        .resize(400, 400)
        .centerCrop()
        .error(R.drawable.ic_baseline_error_24)
        .into(this, object: Callback {
            override fun onSuccess() {
                progressBar.visibility = View.GONE
            }

            override fun onError(e: Exception?) { }
        })
}

//val DB_NAME = "umcdb"
//
//fun buildDb(context: Context): UmcDatabase {
//    val db = Room.databaseBuilder(context, UmcDatabase::class.java, DB_NAME)
//        .build()
//    return db
//}

val DB_NAME = "umcdb"

fun buildDb(context: Context):UmcDatabase{
    val db = Room.databaseBuilder(context, UmcDatabase::class.java, DB_NAME)
        .fallbackToDestructiveMigration()
        .addCallback(object: RoomDatabase.Callback(){
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)

                Executors.newSingleThreadExecutor().execute {
                    db.execSQL("INSERT INTO News (title, description, photo_url, date) VALUES ('Mask Required', 'Due to this COVID pandemic, you are required to use either one N95 mask or double layered medic 3ply mask when entering our facility.', 'https://neilson.id/projectcollege/umc/image/mask.jpg', 'April 6th 2022'), ('Free Medicine Delivery', 'Our delivery on medicine purchase is now free for customers in Surabaya! The fee is now borne by the government as a form of assistance due to this pandemic.', 'https://neilson.id/projectcollege/umc/image/medicine1.jpg', 'April 21th 2022'), ('Healthcare Renovation', 'Great News incoming! Our west building will be equipped with an elevator now and some new facility.', 'https://neilson.id/projectcollege/umc/image/hospital.jpg', 'May 1st 2022'), ('Ramadhan Discount', 'Get a 10% off discount on all medicines purchase on this week only until June 5th 2022! You have to make the purchase through Ubaya Medical Center application.', 'none', 'May 28th 2022'), ('New Checkup Category', 'Our hospital is now able to check your teeth with our new professional dentist! We make sure your teeth will look as good as diamond!', 'none', 'June 2nd 2022')")
                }
            }
        })
        .build()
    return db
}

//fun buildDbNews(context: Context):NewsDatabase {
//    val db = Room.databaseBuilder(context, NewsDatabase::class.java, DB_NAME)
//        .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
//        .build()
//    return db
//}

//val MIGRATION_1_2 = object :  Migration(1, 2) {
//    override fun migrate(database: SupportSQLiteDatabase) {
//        database.execSQL(
//            "CREATE TABLE `doctor` (`uuid` INTEGER, `name` TEXT, `gender` TEXT, `price` INTEGER, `photo_url` TEXT, `doctor_category` TEXT, `doctor_category_description` TEXT)"
//        )
//    }
//}
//
//val MIGRATION_2_3 = object :  Migration(2, 3) {
//    override fun migrate(database: SupportSQLiteDatabase) {
//        database.execSQL(
//            "CREATE TABLE `appointment` (`uuid` INTEGER, `user` TEXT, `doctor` INTEGER, `time` INTEGER)"
//        )
//    }
//}



//fun buildDb(context: Context):UserDatabase {
//    val db = Room.databaseBuilder(context, UserDatabase::class.java, DB_USER)
//        .addMigrations(MIGRATION_1_2)
//        .build()
//
//    return db
//}


//val MIGRATION_1_2 = object : Migration(1, 2) {
//    override fun migrate(database: SupportSQLiteDatabase) {
//        database.execSQL(
//            "INSERT INTO `users_medicines` (`users_username`, `medicines_id`, `quantity`, `total`, `time`) VALUES ('neilson', 3, 3, 24000, 'March 8th 2022. 19.00'), ('neilson', 10, 2, 33000, 'June 3rd 2022. 16.36'), ('neilson', 16, 1, 35000, 'July 21th 2022. 08.01')"
//        )
//    }
//}