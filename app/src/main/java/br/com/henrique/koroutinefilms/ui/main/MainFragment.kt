package br.com.henrique.koroutinefilms.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import br.com.henrique.koroutinefilms.R
//import br.com.henrique.koroutinefilms.R.id.textviewfilms
class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var textViewFilm: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.main_fragment, container, false)
        textViewFilm = view.findViewById(R.id.textviewfilms)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, MainViewModelFactory(MainRepository())).get(MainViewModel::class.java)

        viewModel.filmsLiveData.observe(viewLifecycleOwner, {
            filmes -> textViewFilm.text = filmes[0].titulo
        })

        viewModel.getFilmsCoroutine()

    }

}