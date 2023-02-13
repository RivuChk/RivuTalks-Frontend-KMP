import dev.rivu.rivutalks.common.di.initKoin
import dev.rivu.rivutalks.common.repository.RivuTalksRepositoryInterface
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import react.child
import react.createContext
import react.dom.render
import kotlinx.browser.document


object AppDependencies : KoinComponent {
    val rivutalksRepository: RivuTalksRepositoryInterface

    init {
        initKoin()
        rivutalksRepository = get()
    }
}

val AppDependenciesContext = createContext<AppDependencies>()

@InternalCoroutinesApi
fun main() {
    render(kotlinx.browser.document.getElementById("root")) {
        AppDependenciesContext.Provider(AppDependencies) {
            child(App)
        }
    }
}