package com.example.ortalamahesaplama1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.shashank.sony.fancytoastlib.FancyToast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.yeni_ders_layout.view.*

class MainActivity : AppCompatActivity() {

    private val DERSLER = arrayOf("Matematik","Türkçe","Fizik","Algoritma","Tarih")
    private var tumDerslerinBilgileri:ArrayList<Dersler> = ArrayList(5)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var adapter = ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, DERSLER)

        etDersAd.setAdapter(adapter)

        if (rootLayout.childCount == 0) {
            btnOrtalamaHesapla.visibility = View.INVISIBLE
        } else btnOrtalamaHesapla.visibility = View.VISIBLE


        btnDersEkle.setOnClickListener {

            if (!etDersAd.text.isNullOrEmpty()){
                var inflater = LayoutInflater.from(this)

                var yeniDersView = inflater.inflate(R.layout.yeni_ders_layout,null)

                yeniDersView.etYeniDersAd.setAdapter(adapter)

                var dersAdi = etDersAd.text.toString()
                var dersKredi = spnDersKredi.selectedItem.toString()
                var dersHarf = spnDersNot.selectedItem.toString()

                yeniDersView.etYeniDersAd.setText(dersAdi)
                yeniDersView.spnYeniDersKredi.setSelection(spinnerDegerinIndexiniBul(spnDersKredi,dersKredi))
                yeniDersView.spnYeniDersNot.setSelection(spinnerDegerinIndexiniBul(spnDersNot,dersHarf))

                yeniDersView.btnDersSil.setOnClickListener {
                    rootLayout.removeView(yeniDersView)


                    if (rootLayout.childCount == 0){
                        btnOrtalamaHesapla.visibility = View.INVISIBLE
                    }else btnOrtalamaHesapla.visibility = View.VISIBLE
                    }


                rootLayout.addView(yeniDersView)

                btnOrtalamaHesapla.visibility = View.VISIBLE

                sifirla()

                }
            else{
                FancyToast.makeText(this,"Ders Adını Giriniz",FancyToast.LENGTH_LONG,FancyToast.WARNING,true).show()

            }
        }
    }
    fun sifirla(){
        etDersAd.setText("")
        spnDersKredi.setSelection(0)
        spnDersNot.setSelection(0)
    }

    fun spinnerDegerinIndexiniBul(spinner: Spinner,aranacakDeger:String):Int {
        var index = 0
        for (i in 0..spinner.count){

            if (spinner.getItemAtPosition(i).toString().equals(aranacakDeger)){
                index = i
                break
            }
        }
        return index
    }
    fun ortalamaHesapla(view: View) {

        var toplamNot: Double = 0.0
        var toplamKredi = 0.0

        for (i in 0..rootLayout.childCount - 1) {

            var tekSatir = rootLayout.getChildAt(i)

            var geciciDers = Dersler(tekSatir.etYeniDersAd.text.toString(),
                    ((tekSatir.spnYeniDersKredi.selectedItemPosition) + 1).toString(),
                    tekSatir.spnYeniDersNot.selectedItem.toString())

            tumDerslerinBilgileri.add(geciciDers)
        }
        for (oankiDers in tumDerslerinBilgileri) {

            toplamNot += harfiNotaCevir(oankiDers.dersHarfNotu) * (oankiDers.dersKredi.toDouble())
            toplamKredi += oankiDers.dersKredi.toDouble()

        }

        FancyToast.makeText(this,"ORTALAMA: " + (toplamNot/toplamKredi),FancyToast.LENGTH_LONG,FancyToast.WARNING,true).show()

        tumDerslerinBilgileri.clear()

    }

    fun harfiNotaCevir(gelenNotHarfDegeri:String): Double {

        var deger = 0.0

        when(gelenNotHarfDegeri){

            "AA" -> deger=4.0
            "BA" -> deger=3.5
            "BB" -> deger=3.0
            "CB" -> deger=2.5
            "CC" -> deger=2.0
            "DC" -> deger=1.5
            "DD" -> deger=1.0
            "FF" -> deger=0.5
        }
        return deger
    }


}
