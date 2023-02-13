
import components.BlogList
import components.Typography
import components.materialui.AppBar
import components.materialui.Avatar
import components.materialui.Grid
import components.materialui.Toolbar
import dev.rivu.rivutalks.common.remote.Blog
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.css.margin
import kotlinx.css.px
import react.Fragment
import react.RProps
import react.functionalComponent
import react.useContext
import react.useEffectWithCleanup
import react.useState
import styled.css


@InternalCoroutinesApi
val App = functionalComponent<RProps> {
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
                    Typography("h4", "Rivu Talks")

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