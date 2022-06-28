package com.ubaya.s160419037_umc.util

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.ubaya.s160419037_umc.R
import com.ubaya.s160419037_umc.model.DoctorDatabase
import com.ubaya.s160419037_umc.model.NewsDatabase
import com.ubaya.s160419037_umc.model.UmcDatabase
import com.ubaya.s160419037_umc.model.UserDatabase
import java.lang.Exception

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

val DB_NAME = "newdb"

fun buildDb(context: Context): UmcDatabase {
    val db = Room.databaseBuilder(context, UmcDatabase::class.java, DB_NAME)
        .addMigrations(MIGRATION_1_2)
        .build()
    return db
}

fun buildDbNews(context: Context):NewsDatabase {
    val db = Room.databaseBuilder(context, NewsDatabase::class.java, DB_NAME)
        .addMigrations(MIGRATION_1_2)
        .build()
    return db
}

val MIGRATION_1_2 = object :  Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "CREATE TABLE `doctor` (`uuid` INTEGER, `name` TEXT, `gender` TEXT, `price` INTEGER, `photo_url` TEXT, `doctor_category` TEXT, `doctor_category_description` TEXT)"
        )
        database.execSQL(
            "INSERT INTO doctor (uuid, name, gender, price, photo_url, doctor_categories_id) VALUES (1, 'Dr Fillmore', 'Male', 15000, 'https://i.pravatar.cc/500?img=60', 1), (2, 'Dr Drewel', 'Male', 10000, 'https://i.pravatar.cc/500?img=59', 1), (3, 'Dr Chu', 'Female', 8000, 'https://i.pravatar.cc/500?img=47', 2), (4, 'Dr Hurter', 'Male', 20000, 'https://i.pravatar.cc/500?img=12', 3), (5, 'Dr Pepper', 'Female', 19000, 'https://i.pravatar.cc/500?img=32', 3), (6, 'Dr Beavers', 'Male', 15000, 'https://i.pravatar.cc/500?img=33', 3), (7, 'Dr Luke Whitesell', 'Male', 16000, 'https://i.pravatar.cc/500?img=51', 4), (8, 'Dr Elfman', 'Male', 10000, 'https://i.pravatar.cc/500?img=52', 4), (9, 'Dr Hopper', 'Female', 11000, 'https://i.pravatar.cc/500?img=36', 5), (10, 'Dr Kaufman', 'Male', 18000, 'https://i.pravatar.cc/500?img=53', 6), (11, 'Dr Albright', 'Male', 18500, 'https://i.pravatar.cc/500?img=54', 6), (12, 'Dr Stone', 'Female', 13000, 'https://i.pravatar.cc/500?img=26', 7), (13, 'Dr White', 'Male', 10000, 'https://i.pravatar.cc/500?img=8', 7), (14, 'Dr Luke', 'Male', 11000, 'https://i.pravatar.cc/500?img=57', 7)"
        )
    }
}


val MIGRATION_NEWS_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "INSERT INTO news (uuid, title, description, photo_url, date) VALUES (1, 'Mask Required', 'Due to this COVID pandemic, you are required to use either one N95 mask or double layered medic 3ply mask when entering our facility.', 'https://neilson.id/projectcollege/umc/image/mask.jpg', 'April 6th 2022'), (2, 'Free Medicine Delivery', 'Our delivery on medicine purchase is now free for customers in Surabaya! The fee is now borne by the government as a form of assistance due to this pandemic.', 'https://neilson.id/projectcollege/umc/image/medicine1.jpg', 'April 21th 2022'), (3, 'Healthcare Renovation', 'Great News incoming! Our west building will be equipped with an elevator now and some new facility.', 'https://neilson.id/projectcollege/umc/image/hospital.jpg', 'May 1st 2022'), (4, 'Ramadhan Discount', 'Get a 10% off discount on all medicines purchase on this week only until June 5th 2022! You have to make the purchase through Ubaya Medical Center application.', 'none', 'May 28th 2022'), (5, 'New Checkup Category', 'Our hospital is now able to check your teeth with our new professional dentist! We make sure your teeth will look as good as diamond!', 'none', 'June 2nd 2022')"
        )
    }
}

fun buildDbDoctor(context: Context):DoctorDatabase {
    val db = Room.databaseBuilder(context, DoctorDatabase::class.java, DB_NAME)
        .addMigrations(MIGRATION_DOCTORS_1_2)
        .build()
    return db
}

val MIGRATION_DOCTORS_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "INSERT INTO doctor (uuid, name, gender, price, photo_url, doctor_categories_id) VALUES (1, 'Dr Fillmore', 'Male', 15000, 'https://i.pravatar.cc/500?img=60', 1), (2, 'Dr Drewel', 'Male', 10000, 'https://i.pravatar.cc/500?img=59', 1), (3, 'Dr Chu', 'Female', 8000, 'https://i.pravatar.cc/500?img=47', 2), (4, 'Dr Hurter', 'Male', 20000, 'https://i.pravatar.cc/500?img=12', 3), (5, 'Dr Pepper', 'Female', 19000, 'https://i.pravatar.cc/500?img=32', 3), (6, 'Dr Beavers', 'Male', 15000, 'https://i.pravatar.cc/500?img=33', 3), (7, 'Dr Luke Whitesell', 'Male', 16000, 'https://i.pravatar.cc/500?img=51', 4), (8, 'Dr Elfman', 'Male', 10000, 'https://i.pravatar.cc/500?img=52', 4), (9, 'Dr Hopper', 'Female', 11000, 'https://i.pravatar.cc/500?img=36', 5), (10, 'Dr Kaufman', 'Male', 18000, 'https://i.pravatar.cc/500?img=53', 6), (11, 'Dr Albright', 'Male', 18500, 'https://i.pravatar.cc/500?img=54', 6), (12, 'Dr Stone', 'Female', 13000, 'https://i.pravatar.cc/500?img=26', 7), (13, 'Dr White', 'Male', 10000, 'https://i.pravatar.cc/500?img=8', 7), (14, 'Dr Luke', 'Male', 11000, 'https://i.pravatar.cc/500?img=57', 7)"
        )
    }
}

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