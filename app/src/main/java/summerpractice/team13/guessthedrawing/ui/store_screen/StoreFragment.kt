package summerpractice.team13.guessthedrawing.ui.store_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import summerpractice.team13.guessthedrawing.R

class StoreFragment : Fragment() {

    private lateinit var storeViewModel: StoreViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        storeViewModel =
            ViewModelProvider(this).get(StoreViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_store, container, false)
        //val textView: TextView = root.findViewById(R.id.text_store)
//        storeViewModel.text.observe(viewLifecycleOwner, {
//            textView.text = it
//        })
        return root
    }
}