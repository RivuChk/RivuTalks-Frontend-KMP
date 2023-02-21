package components

import AppDependenciesContext
import components.materialui.CircularProgress
import dev.rivu.rivutalks.common.remote.models.Blog
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.html.html
import kotlinx.html.stream.appendHTML
import kotlinx.html.stream.createHTML
import kotlinx.html.unsafe
import org.intellij.markdown.flavours.commonmark.CommonMarkFlavourDescriptor
import org.intellij.markdown.html.HtmlGenerator
import org.intellij.markdown.parser.MarkdownParser
import org.w3c.dom.Element
import react.RProps
import react.dom.div
import react.dom.html
import react.dom.iframe
import react.dom.p
import react.dom.render
import react.functionalComponent
import react.useContext
import react.useEffectWithCleanup
import react.useState

val AboutMeSection = functionalComponent<RProps> {
    val appDependencies = useContext(AppDependenciesContext)
    val rivutalksRepository = appDependencies.rivutalksRepository


    val (aboutMeDetails, setAboutMeDetails) = useState("")
    val (aboutMeHeader, setAboutMeHeader) = useState("")
    val (loading, setLoading) = useState(false)

    useEffectWithCleanup(dependencies = listOf()) {
        val mainScope = MainScope()

        mainScope.launch {
            setLoading(true)
            val aboutMe = rivutalksRepository.fetchAboutMe()
            setAboutMeDetails(aboutMe.aboutMeDetails)
            setAboutMeHeader(aboutMe.headline)
            setLoading(false)
        }
        return@useEffectWithCleanup { mainScope.cancel() }
    }
    if (loading) {
        CircularProgress {}
    } else {
        div {
            Typography("h4", aboutMeHeader)

            div {
                attrs {

                    val flavour = CommonMarkFlavourDescriptor()
                    val parsedTree = MarkdownParser(flavour).buildMarkdownTreeFromString(aboutMeDetails)
                    val htmlStr = HtmlGenerator(aboutMeDetails, parsedTree, flavour).generateHtml()

                    unsafe {
                        +htmlStr//breaks the app
                    }

                }



            }
        }

    }
}