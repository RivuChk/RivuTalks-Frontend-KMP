
import AppDependencies.rivutalksRepository
import components.BlogList
import components.BlogSection
import components.Typography
import components.VideosSection
import components.materialui.AppBar
import components.materialui.Avatar
import components.materialui.Grid
import components.materialui.Tab
import components.materialui.TabPanel
import components.materialui.Tabs
import components.materialui.Toolbar
import components.models.Section
import dev.rivu.rivutalks.common.remote.models.Blog
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.NonCancellable.children
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.css.Color
import kotlinx.css.background
import kotlinx.css.margin
import kotlinx.css.px
import org.w3c.dom.events.Event
import react.Fragment
import react.RProps
import react.child
import react.dom.div
import react.functionalComponent
import react.useContext
import react.useEffectWithCleanup
import react.useState
import styled.css


@InternalCoroutinesApi
val App = functionalComponent<RProps> {

    val sections = listOf(
        Section.VideosSection, Section.BlogSection, Section.AboutSection
    )
    val (selectedSection, setSelectedSection) = useState<Section>(Section.AboutSection)

    useEffectWithCleanup(dependencies = listOf()) {
        val mainScope = MainScope()

        mainScope.launch {
            setSelectedSection(sections.first())
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

        div {
            //TODO: Figure out Tab Selection
            Tabs {
                attrs {
                    orientation = vertical
                    selectionFollowsFocus = true
                    textColor = primary
                    indicatorColor = secondary
                    onChange = { _: Event, value: Any ->
                        setSelectedSection(
                            sections.first{
                                it.index == value
                            }
                        )
                    }
                }
                sections.forEach {
                    Tab {
                        attrs {
                            label = it.title
                            value = it.index
                            selected = it.index == selectedSection.index
                        }
                    }
                }
            }
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
                //TODO: Figure out why TabPanel not working
                when(selectedSection) {
                    is Section.BlogSection -> div {
                        child(BlogSection)
                    }
                    is Section.AboutSection -> div {
                        child(
                            div {
                                Typography("h1", "Something about me will go here.")
                                Typography("h4", "Wait for it. Everything good requires a little patience")
                            }
                        )
                    }
                    is Section.VideosSection -> div {
                        child(VideosSection)
                    }
                }
            }

        }

    }
}