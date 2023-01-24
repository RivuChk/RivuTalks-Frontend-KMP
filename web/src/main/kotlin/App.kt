import dev.rivu.rivutalks.common.remote.Assignment
import dev.rivu.rivutalks.common.remote.IssPosition
import components.*
import components.materialui.*
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
    val (issPosition, setIssPosition) = useState(IssPosition(0.0, 0.0))
    val (selectedPerson, setSelectedPerson) = useState<Assignment?>(null)

    useEffectWithCleanup(dependencies = listOf()) {
        val mainScope = MainScope()

        mainScope.launch {
            val people = peopleRepository.fetchPeople()
            setPeople(people)
            setSelectedPerson(people.first())

            peopleRepository.pollISSPosition().collect {
                setIssPosition(it)
            }
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
                        justify = "flex-start"
                        alignItems = "stretch"
                    }
                    Typography("h6", "Rivu Talks")

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
                    md = 4
                    xs = 12
                }
                PeopleList(
                    people = people,
                    selectedPerson = selectedPerson,
                    onSelect = {
                        setSelectedPerson(it)
                    }
                )
            }
            Grid {
                attrs {
                    item = true
                    md = 8
                    xs = 12
                }

                selectedPerson?.let { person ->
                    Card {
                        css {
                            padding(16.px)
                        }

                        PersonDetails(person)
                    }
                }
            }
        }

    }
}