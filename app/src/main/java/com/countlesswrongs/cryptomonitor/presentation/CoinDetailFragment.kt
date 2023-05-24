package com.countlesswrongs.cryptomonitor.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.countlesswrongs.cryptomonitor.databinding.FragmentCoinDetailBinding
import com.countlesswrongs.cryptomonitor.presentation.viewmodel.CoinViewModel
import com.countlesswrongs.cryptomonitor.presentation.viewmodel.ViewModelFactory
import com.squareup.picasso.Picasso
import javax.inject.Inject

class CoinDetailFragment : Fragment() {

    private lateinit var viewModel: CoinViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireActivity().application as CoinApp).component
    }

    private var _binding: FragmentCoinDetailBinding? = null
    private val binding: FragmentCoinDetailBinding
        get() = _binding ?: throw RuntimeException("FragmentCoinDetailBinding is NULL")

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoinDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fSym = getSymbol()
        viewModel = ViewModelProvider(this, viewModelFactory)[CoinViewModel::class.java]
        viewModel.getDetailedInfo(fSym).observe(viewLifecycleOwner) {
            with(binding) {
                textViewFromSymbol.text = it.fromSymbol
                textViewToSymbol.text = it.toSymbol
                textViewPrice.text = it.price
                textViewLowestPriceToday.text = it.lowDay
                textViewHighestPriceToday.text = it.highDay
                textViewLastMarket.text = it.lastMarket
                textViewLastUpdate.text = it.lastUpdate
                Picasso.get().load(it.imageUrl).into(imageViewLogo)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun getSymbol() = requireArguments().getString(EXTRA_FROM_SYMBOL, EMPTY_SYMBOL_STRING)

    companion object {
        private const val EXTRA_FROM_SYMBOL = "fSym"
        private const val EMPTY_SYMBOL_STRING = ""

        fun newInstance(fromSymbol: String): Fragment {
            return CoinDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_FROM_SYMBOL, fromSymbol)
                }
            }
        }
    }
}
