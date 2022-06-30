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

val DB_NAME = "umcdb"

fun buildDb(context: Context):UmcDatabase{
    val db = Room.databaseBuilder(context, UmcDatabase::class.java, DB_NAME)
        .fallbackToDestructiveMigration()
        .addCallback(object: RoomDatabase.Callback(){
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)

                Executors.newSingleThreadExecutor().execute {
                    db.execSQL("INSERT INTO News (title, description, photo_url, date) VALUES ('Mask Required', 'Due to this COVID pandemic, you are required to use either one N95 mask or double layered medic 3ply mask when entering our facility.', 'https://neilson.id/projectcollege/umc/image/mask.jpg', 'April 6th 2022'), ('Free Medicine Delivery', 'Our delivery on medicine purchase is now free for customers in Surabaya! The fee is now borne by the government as a form of assistance due to this pandemic.', 'https://neilson.id/projectcollege/umc/image/medicine1.jpg', 'April 21th 2022'), ('Healthcare Renovation', 'Great News incoming! Our west building will be equipped with an elevator now and some new facility.', 'https://neilson.id/projectcollege/umc/image/hospital.jpg', 'May 1st 2022'), ('Ramadhan Discount', 'Get a 10% off discount on all medicines purchase on this week only until June 5th 2022! You have to make the purchase through Ubaya Medical Center application.', 'none', 'May 28th 2022'), ('New Checkup Category', 'Our hospital is now able to check your teeth with our new professional dentist! We make sure your teeth will look as good as diamond!', 'none', 'June 2nd 2022')")
                    db.execSQL("INSERT INTO Doctor (name, gender, price, photo_url, doctor_category, doctor_category_description) VALUES ('Dr Fillmore', 'Male', 15000, 'https://i.pravatar.cc/500?img=60', 'Allergists', 'An allergist or immunologist focuses on preventing and treating allergic diseases and conditions. These usually include various types of allergies and asthma.'), ('Dr Drewel', 'Male', 10000, 'https://i.pravatar.cc/500?img=59', 'Allergists', 'An allergist or immunologist focuses on preventing and treating allergic diseases and conditions. These usually include various types of allergies and asthma.'), ('Dr Chu', 'Female', 8000, 'https://i.pravatar.cc/500?img=47','Dermatologists','Dermatologists focus on diseases and conditions of the skin, nails, and hair. They treat conditions such as eczema, skin cancer, acne, and psoriasis.'), ('Dr Hurter', 'Male', 20000, 'https://i.pravatar.cc/500?img=12', 'Ophthalmologists', 'Ophthalmologists specialize in eye and vision care. They treat diseases and conditions of the eyes and can perform eye surgery.'), ('Dr Pepper', 'Female', 19000, 'https://i.pravatar.cc/500?img=32', 'Ophthalmologists', 'Ophthalmologists specialize in eye and vision care. They treat diseases and conditions of the eyes and can perform eye surgery.'), ('Dr Beavers', 'Male', 15000, 'https://i.pravatar.cc/500?img=33', 'Ophthalmologists', 'Ophthalmologists specialize in eye and vision care. They treat diseases and conditions of the eyes and can perform eye surgery.'), ('Dr Luke Whitesell', 'Male', 16000, 'https://i.pravatar.cc/500?img=51', 'Obstetrician', 'Obstetrician/gynecologists (OB/GYNs) provide preventive care and disease management for female health conditions.'), ('Dr Elfman', 'Male', 10000, 'https://i.pravatar.cc/500?img=52', 'Obstetrician', 'Obstetrician/gynecologists (OB/GYNs) provide preventive care and disease management for female health conditions.'), ('Dr Hopper', 'Female', 11000, 'https://i.pravatar.cc/500?img=36', 'Nephrologists', 'A nephrologist focuses on kidney care and conditions that affect the kidneys.'), ('Dr Kaufman', 'Male', 18000, 'https://i.pravatar.cc/500?img=53', 'Urologists', 'Urologists treat conditions of the urinary tract in both males and females. They also focus on male reproductive health.')")
                    db.execSQL("INSERT INTO Medicine (name, variant, price, description, photo_url, medicine_category) VALUES ('asam mefenamat', 'kaps 250 mg', 10000, 'Asam mefenamat atau mefenamic acid adalah obat yang berfungsi untuk meredakan nyeri, seperti sakit gigi, sakit kepala, dan nyeri haid', 'https://neilson.id/projectcollege/umc/image/asammefenamat250.jpg', 'ANALGESIK NON NARKOTIK'), ('asam mefenamat', 'tab 500 mg', 12000, 'Asam mefenamat atau mefenamic acid adalah obat yang berfungsi untuk meredakan nyeri, seperti sakit gigi, sakit kepala, dan nyeri haid', 'https://neilson.id/projectcollege/umc/image/asammefenamat500.jpg', 'ANALGESIK NON NARKOTIK'), ('ibuprofen', 'tab 200 mg', 8000, 'Ibuprofen adalah obat yang digunakan untuk meredakan nyeri dan peradangan, misalnya sakit gigi, nyeri haid, dan radang sendi.', 'https://neilson.id/projectcollege/umc/image/ibuprofen200.jpg', 'ANALGESIK NON NARKOTIK'), ('ibuprofen', 'tab 400 mg', 9500, 'Ibuprofen adalah obat yang digunakan untuk meredakan nyeri dan peradangan, misalnya sakit gigi, nyeri haid, dan radang sendi.', 'https://neilson.id/projectcollege/umc/image/ibuprofen400.jpg', 'ANALGESIK NON NARKOTIK'), ('asam mefenamat', 'susp 100 mg/5 mL', 15000, 'Asam mefenamat atau mefenamic acid adalah obat yang berfungsi untuk meredakan nyeri, seperti sakit gigi, sakit kepala, dan nyeri haid', 'https://neilson.id/projectcollege/umc/image/asammefenamat100.jpg', 'ANALGESIK NON NARKOTIK'), ('ketoprofen', 'inj 50 mg/mL', 22000, 'Ketoprofen adalah obat untuk meredakan rasa sakit, bengkak, dan kaku akibat cedera, radang sendi (arthritis), dan nyeri haid.', 'https://neilson.id/projectcollege/umc/image/ketoprofen50.jpg', 'ANALGESIK NON NARKOTIK'), ('ketoprofen', 'sup 100 mg', 25000, 'Ketoprofen adalah obat untuk meredakan rasa sakit, bengkak, dan kaku akibat cedera, radang sendi (arthritis), dan nyeri haid.', 'https://neilson.id/projectcollege/umc/image/ketoprofen100.jpg', 'ANALGESIK NON NARKOTIK'), ('alopurinol', 'tab 100 mg', 17500, 'Alopurinol adalah obat yang umumnya diresepkan untuk mengobati penyakit asam urat dan batu ginjal jenis tertentu.', 'https://neilson.id/projectcollege/umc/image/alopurinol100.jpg', 'ANALGESIK NON NARKOTIK'), ('alopurinol', 'tab 300 mg', 17500, 'Alopurinol adalah obat yang umumnya diresepkan untuk mengobati penyakit asam urat dan batu ginjal jenis tertentu.', 'https://neilson.id/projectcollege/umc/image/alopurinol300.jpg', 'ANTIPIRAI'), ('kolkisin', 'tab 500 mcg', 16500, 'Kolkisin atau colchicine adalah obat yang biasanya diberikan untuk mengatasi serangan asam urat akut atau mendadak.', 'https://neilson.id/projectcollege/umc/image/kolkisin.jpg', 'ANTIPIRAI'), ('bupivakain', 'inj 0,5%', 12250, 'Bupivakain (bupivacaine) adalah obat dengan fungsi untuk menghilangkan rasa sakit selama prosedur medis dan operasi, termasuk bedah persalinan dan bedah mulut.', 'https://neilson.id/projectcollege/umc/image/bupivakain.jpg', 'ANESTETIK LOKAL'), ('lidokain', 'inj 0,5%', 12250, 'Lidocaine adalah obat untuk menghilangkan rasa sakit atau memberi efek mati rasa pada bagian tubuh tertentu (obat bius lokal).', 'https://neilson.id/projectcollege/umc/image/lidokain05.jpg', 'ANESTETIK LOKAL'), ('lidokain', 'spray topikal 10%', 12250, 'Lidocaine adalah obat untuk menghilangkan rasa sakit atau memberi efek mati rasa pada bagian tubuh tertentu (obat bius lokal).', 'https://neilson.id/projectcollege/umc/image/lidokain10.jpg', 'ANESTETIK LOKAL'), ('propranolol', 'tab 10 mg', 25250, 'Propranolol adalah obat untuk mengobati berbagai kondisi yang berhubungan dengan jantung dan pembuluh darah, seperti aritmia, hipertensi, hypertrophic subaortic stenosis, atau hipertensi portal.', 'https://neilson.id/projectcollege/umc/image/propanolol.jpg', 'ANTIMIGREN'), ('betahistin', 'tab 6 mg', 25250, 'Betahistine adalah obat untuk meredakan keluhan vertigo, gangguan pendengaran, dan telinga berdenging (tinnitus) yang disebabkan oleh penyakit Meniere.', 'https://neilson.id/projectcollege/umc/image/betahistin6.jpg', 'ANTIVERTIGO'), ('betahistin', 'tab 24 mg', 35000, 'Betahistine adalah obat untuk meredakan keluhan vertigo, gangguan pendengaran, dan telinga berdenging (tinnitus) yang disebabkan oleh penyakit Meniere.', 'https://neilson.id/projectcollege/umc/image/betahistin24.jpg', 'ANTIVERTIGO')")
                    db.execSQL("INSERT INTO User (username,  name, password, email, phone, address, photo_url) VALUES ('neilson', 'Neilson Soeratman', 'neilson', 'neilsonsoeratman@gmail.com', '082337363440', 'Jl. Raya Semampir Barat no. 2', 'https://i.pravatar.cc/500?img=56')")
                }
            }
        })
        .addMigrations(MIGRATION_1_2)
        .build()
    return db
}

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "ALTER TABLE doctor ADD COLUMN time TEXT DEFAULT 'Monday 10.00-12.00' NOT NULL"
        )
    }
}














