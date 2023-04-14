# this is for research purposes; trying to create alternative color palettes :]

from coloraide import Color

colors = Color.steps(
    [Color("#640C35"),
    Color("#E31C79"),
    Color("#EC67A5"),
    Color("#1CE386"),
    Color("#13995B"),],
    space="srgb",
    out_space="srgb",
    steps=16
)

for c in colors:
    print(c.to_string(rgb=True))

"""

Color("#640C35"),
Color("#E31C79"),
Color("#EC67A5"),
Color("#1CE386"),
Color("#13995B"),


RGB(133, 16, 71)
RGB(100, 12, 53)
RGB(167, 20, 89)
RGB(201, 24, 107)
RGB(227, 33, 123)
RGB(230, 53, 135)
RGB(232, 73, 147)
RGB(234, 93, 159)
RGB(208, 119, 160)
RGB(152, 152, 152)
RGB(97, 185, 144)
RGB(41, 218, 136)
RGB(26, 212, 125)
RGB(23, 192, 113)
RGB(21, 172, 102)
RGB(19, 153, 91)

"""