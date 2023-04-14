# this is for research purposes; trying to create alternative color palettes :]

from coloraide import Color

colors = Color.steps(
    [Color("#020369"), Color("#FFFFFF"), Color("#696702")],
    space="lch",
    out_space="srgb",
    steps=16
)

for c in colors[::-1]:
    print(c.to_string(rgb=True))