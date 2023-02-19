package components

import AppDependenciesContext
import dev.rivu.rivutalks.common.remote.models.Blog
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import react.RProps
import react.functionalComponent
import react.useContext
import react.useEffectWithCleanup
import react.useState

val BlogSection = functionalComponent<RProps> {
    val appDependencies = useContext(AppDependenciesContext)
    val rivutalksRepository = appDependencies.rivutalksRepository

    val (blogs, setBlogs) = useState(emptyList<Blog>())
    val (selectedBlog, setSelectedBlog) = useState<Blog?>(null)

    useEffectWithCleanup(dependencies = listOf()) {
        val mainScope = MainScope()

        mainScope.launch {
            val blogs = rivutalksRepository.fetchBlogs()
            setBlogs(blogs)
            setSelectedBlog(blogs.firstOrNull())

        }
        return@useEffectWithCleanup { mainScope.cancel() }
    }

    BlogList(
        blogs = blogs,
        selectedBlog = selectedBlog,
        onSelect = {
            setSelectedBlog(it)
        }
    )
}