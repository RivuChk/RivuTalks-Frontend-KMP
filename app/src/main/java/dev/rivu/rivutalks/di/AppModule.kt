package dev.rivu.rivutalks.di


import dev.rivu.rivutalks.videos.VideosViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::VideosViewModel)
    /*viewModel { PersonDetailsViewModel(get(), get()) }
    viewModel { ISSPositionViewModel(peopleInSpaceRepository = get()) }*/
}
