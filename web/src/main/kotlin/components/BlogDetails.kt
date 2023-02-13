package components

import dev.rivu.rivutalks.common.remote.Blog
import kotlinx.css.Align
import kotlinx.css.alignSelf
import kotlinx.css.margin
import kotlinx.css.padding
import kotlinx.css.px
import react.RBuilder
import react.dom.a
import styled.css
import styled.styledDiv
import styled.styledIframe
import styled.styledImg

fun RBuilder.BlogDetails(blog: Blog) {
    styledDiv {
        css {
            padding(32.px)
        }
        styledImg(alt = blog.title, src = blog.featureImage) {
            attrs {
                width = "520"
            }
        }

        styledDiv {
            css {
                margin(top = 16.px)
                alignSelf = Align.center
            }
            a(href = blog.url, target = "_blank") {
                Typography("h4", blog.title)
            }
        }

        styledDiv {
            css {
                margin(top = 8.px)
                alignSelf = Align.center
            }
            a(href = blog.site.url, target = "_blank") {
                Typography("h6", blog.site.title)
            }
        }

        styledDiv {
            css {
                margin(top = 24.px)
            }
            blog.summary
        }
    }
}
