package com.example.vicky.contactreader

class ContactAdapter(items : List<ContactDTO>,ctx: Context) : RecyclerView.Adapter<ContactAdapter.ViewHolder>(){

    private var list = items
    private var context = ctx

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ContactAdapter.ViewHolder, position: Int) {
        holder.name.text = list[position].name
        holder.number.text = list[position].number
        if(list[position].image != null)
            holder.profile.setImageBitmap(list[position].image)
        else
            holder.profile.setImageDrawable(ContextCompat.getDrawable(context,R.mipmap.ic_launcher_round))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.contact_child,parent,false))
    }


    class ViewHolder(v: View) : RecyclerView.ViewHolder(v){
        val name = v.tv_name!!
        val number = v.tv_number!!
        val profile = v.iv_profile!!
    }
}