package components

import components.materialui.*
import dev.rivu.rivutalks.common.remote.Blog
import kotlinx.css.*
import react.RBuilder
import react.dom.a
import styled.css
import styled.styledDiv
import styled.styledImg

fun RBuilder.BlogList(
    blogs: List<Blog>,
    selectedBlog: Blog?,
    onSelect: (Blog) -> Unit
) {
    List {
        blogs.forEach { blog ->
            val isSelected = blog == selectedBlog
            ListItem {
                attrs {
                    this.button = true
                    key = blog.title
                    selected = isSelected
                    onClick = {
                        onSelect(item)
                    }
                }
                Card {
                    Grid {
                        attrs {
                            container = true
                            md = 12
                            xs = 12
                        }
                        Grid {
                            attrs {
                                item = true
                                md = 6
                                xs = 12
                            }
                            css {
                                display = Display.inlineBlock
                            }
                            styledImg(alt = blog.title, src = blog.featureImage) {
                                attrs {
                                    width = "100%"
                                }
                            }
                        }
                        Grid {
                            attrs {
                                item = true
                                md = 6
                                xs = 12
                            }
                            css {
                                display = Display.inlineBlock
                            }
                            styledDiv {
                                css {
                                    padding(32.px)
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
                                    Typography("body", blog.summary)
                                }
                            }
                        }
                    }
                }


            }
        }
    }
}