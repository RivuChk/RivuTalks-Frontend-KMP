import dev.rivu.rivutalks.common.remote.Assignment
import dev.rivu.rivutalks.common.remote.IssPosition
import components.*
import components.materialui.*
import dev.rivu.rivutalks.common.remote.Blog
import kotlinx.coroutines.*
import kotlinx.css.margin
import kotlinx.css.padding
import kotlinx.css.px
import react.*
import styled.css


@InternalCoroutinesApi
val App = functionalComponent<RProps> {
    val appDependencies = useContext(AppDependenciesContext)
    val peopleRepository = appDependencies.peopleRepository
    val rivutalksRepository = appDependencies.rivutalksRepository

    val (people, setPeople) = useState(emptyList<Assignment>())
    val (blogs, setBlogs) = useState(emptyList<Blog>())
    val (issPosition, setIssPosition) = useState(IssPosition(0.0, 0.0))
    val (selectedPerson, setSelectedPerson) = useState<Assignment?>(null)
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
    Fragment {
        AppBar {
            css {
                margin(0.px)
            }
            Toolbar {
                Grid {
                    attrs {
                        container = true
                        spacing = 4
                        justify = "flex-start"
                        alignItems = "stretch"
                    }
                    Avatar {
                        attrs.src = "rivu_talks_logo.jpg"
                    }

                }
                Grid {
                    attrs {
                        container = true
                        spacing = 4
                        justify = "flex-mid"
                        alignItems = "stretch"
                    }
                    Typography("h4", "Rivu Talks - Dev")

                }

            }
        }

        Toolbar {
            // Empty toolbar to avoid below content to be overlapped by AppBar
        }

        Grid {
            attrs {
                container = true
                spacing = 4
                justify = "flex-start"
                alignItems = "stretch"
            }

            Grid {
                attrs {
                    item = true
                    md = 12
                    xs = 12
                }
                BlogList(
                    blogs = blogs,
                    selectedBlog = selectedBlog,
                    onSelect = {
                        setSelectedBlog(it)
                    }
                )
            }

        }

    }
}