package components

import dev.rivu.rivutalks.common.remote.Assignment
import kotlinx.css.Align
import kotlinx.css.alignSelf
import kotlinx.css.margin
import kotlinx.css.padding
import kotlinx.css.px
import react.RBuilder
import styled.css
import styled.styledDiv
import styled.styledImg

fun RBuilder.PersonDetails(person: Assignment) {
    styledDiv {
        css {
            padding(32.px)
        }
        styledImg(alt = person.name, src = person.personImageUrl) {
            attrs {
                width = "128"
            }
        }

        styledDiv {
            css {
                margin(top = 16.px)
                alignSelf = Align.center
            }
            Typography("h4", person.name)
        }

        styledDiv {
            css {
                margin(top = 8.px)
                alignSelf = Align.center
            }
            Typography("h6", person.craft)
        }

        styledDiv {
            css {
                margin(top = 24.px)
            }
            Typography("body1", person.personBio ?: "")
        }
    }
}
