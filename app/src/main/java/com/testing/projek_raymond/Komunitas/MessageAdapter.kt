package com.testing.projek_raymond.Komunitas

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import com.testing.projek_raymond.databinding.ItemMessageBinding

/**
 * =========================================================================
 * IMPLEMENTASI CUSTOM LISTVIEW ADAPTER (MENGGUNAKAN ARRAYADAPTER)
 * =========================================================================
 * Adapter ini menghubungkan kumpulan data objek (List<MessageModel>) dengan widget ListView.
 * Setiap baris item di ListView akan dirender menggunakan layout kustom (res/layout/item_message.xml).
 */
class MessageAdapter(
    context: Context,
    private val messages: List<MessageModel>
) : ArrayAdapter<MessageModel>(context, 0, messages) {

    /**
     * FUNGSI getView():
     * Dipanggil otomatis oleh ListView untuk merender setiap baris item pada posisi 'position'.
     * @param position: Posisi indeks item dalam data list (0, 1, 2, dst)
     * @param convertView: View lama yang bisa didaur ulang (recycle view)
     * @param parent: ViewGroup induk (dalam hal ini adalah ListView)
     */
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // 1. Inflate layout baris kustom (item_message.xml) menggunakan ViewBinding
        val binding = ItemMessageBinding.inflate(LayoutInflater.from(context), parent, false)
        val view = binding.root
        val data = messages[position] // Ambil data MessageModel pada posisi baris ini

        // 2. Muat gambar avatar dari URL ke ImageView menggunakan library Glide
        Glide.with(context)
            .load(data.avatarUrl)
            .into(binding.avatarImg)

        // 3. Masukkan data teks pengirim dan ulasan ke TextView
        binding.textSender.text = data.senderName
        binding.textMessage.text = data.messageText

        // 4. Penanganan Klik Baris Item ListView:
        // Saat salah satu baris ulasan diklik, data ulasan dikirim via Intent Extra
        // ke ProfileKomentatorActivity untuk menampilkan profil lengkap komentator.
        view.setOnClickListener {
            val i = Intent(context, ProfileKomentatorActivity::class.java)
            i.putExtra("SENDER", data.senderName)
            i.putExtra("PESAN", data.messageText)
            i.putExtra("BIO", data.bio)
            i.putExtra("AVATAR", data.avatarUrl)
            context.startActivity(i)
        }
        return view
    }
}
