package com.example.kexamplerecycleview3diffutils.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.kexamplerecycleview3diffutils.R
import com.example.kexamplerecycleview3diffutils.model.entity.Note


class NotesRvAdapter() : RecyclerView.Adapter<NotesRvAdapter.ViewHolder> () {

    var notes: List<Note> = listOf()
    set (value){
        field = value
        notifyDataSetChanged()
    }

    // 0. Определяем CLASS ViewHolder
    // т.е. это связь/установка соответствий с макетным файлом itemView (здесь - item_note.xml)
    // т.е. по принципу 'view from xml' = 'data from Note'
    // Самих данных пока нет, только шаблон.
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(note: Note) {
            itemView.findViewById<TextView>(R.id.tv_title).text = note.mTitle
            itemView.findViewById<TextView>(R.id.tv_text).text = note.avatarUrl
            itemView.findViewById<ImageView>(R.id.iv_avatar).load(R.drawable.ic_launcher_foreground)
            // На вход конструктор (?) берет переданную ItemView и разбираем ее на составные части
            // при этом предварительно найти все вьюшки (как в java) здесь не требуется,
            // (но - это расход ресурсов, поэтому нужно как-то закэшировать)

        //      P.S. визуально красивее через with (хотя ... ну, не знаю)
        //        fun bind1(note: Note) = with(itemView){
        //            tv_title.text = note.mTitle
        //            tv_text.text = note.mText
        //            setBackgroundColor(note.mColor)
        }
    }

    // Далее переопределяем ТРИ обязательных метода:

    // 1. Создаем каждый конкретный новый экземпляр viewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false))

    // 2. Наполняем (связываем) viewHolder с данными (подставляем в шаблон конкретные значения Note)
    override fun onBindViewHolder(holder: NotesRvAdapter.ViewHolder, position: Int) = holder.bind(
        notes[position]
    )
    // holder отделен от данных, т.к. это RecycleView, т.е. переиспользуемые созданные холдеры под другие данные

    // 3. Recycler должен знать сколько у него элементов. Данный метод не вызывается из кода
    override fun getItemCount(): Int = notes.size


    fun updateNote(newNotes: List<Note>) {
        this.notes = newNotes
//        notifyDataSetChanged()  - тут не нужно, т.к. определен в
    }

}