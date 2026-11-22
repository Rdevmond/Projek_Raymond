package com.testing.projek_raymond.Note

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.testing.projek_raymond.data.entity.NoteEntity
import com.testing.projek_raymond.databinding.ItemNoteBinding

class NoteAdapter(
    private val notes: List<NoteEntity>,
    private val onDelete: (NoteEntity) -> Unit
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    class NoteViewHolder(val binding: ItemNoteBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NoteViewHolder {
        val binding = ItemNoteBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: NoteViewHolder,
        position: Int
    ) {
        val note = notes[position]
        holder.binding.tvTitle.text = note.title
        holder.binding.tvCategory.text = note.category
        holder.binding.tvPrice.text = note.price
        holder.binding.tvQuantity.text = note.quantity
        if (note.content.isBlank()) {
            holder.binding.tvContent.visibility = View.GONE
        } else {
            holder.binding.tvContent.visibility = View.VISIBLE
            holder.binding.tvContent.text = note.content
        }
        holder.binding.btnDelete.setOnClickListener {
            MaterialAlertDialogBuilder(holder.itemView.context)
                .setTitle("Hapus Catatan")
                .setMessage("Yakin ingin menghapus catatan ini?")
                .setPositiveButton("Ya") { _, _ -> onDelete(note) }
                .setNegativeButton("Batal", null)
                .show()
        }
    }

    override fun getItemCount(): Int = notes.size
}
