package com.sevban.composetutorialssevbanbayir.Canvas

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathMeasure
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.asAndroidPath
import androidx.compose.ui.graphics.vector.PathParser
import androidx.core.graphics.flatten

@Composable
fun DrawOnCanvasWithSVGPath() {
    val path = remember {
        HelloPath.path.toPath()
    }

    val lines = remember {
        path.asAndroidPath().flatten(.5f)
    }

    val progress = remember {
        Animatable(0f)
    }

    val length = remember {
        val pathMeasure = PathMeasure()
        pathMeasure.setPath(path = path, forceClosed = false)
        pathMeasure.length
    }

    LaunchedEffect(key1 = true) {
        progress.animateTo(
            targetValue = 1f,
            animationSpec = infiniteRepeatable(tween(3000), repeatMode = RepeatMode.Reverse)
        )
    }

    Canvas(modifier = Modifier.fillMaxSize()) {
        lines.forEach { line ->
            val currentLengt = length * progress.value
            if (line.startFraction * length < currentLengt) {
                drawLine(
                    brush = Brush.linearGradient(colors),
                    start = Offset(line.start.x, line.start.y),
                    end = Offset(line.end.x, line.end.y),
                    strokeWidth = 30f,
                    cap = StrokeCap.Round
                )
            }
        }
    }
}

private object HelloPath {
    val path = PathParser().parsePathString(
        "M8 360.664C11.5921 360.664 16.6799 355.442 18.9356 352.542C22.3725 348.123 26.0683 343.25 29.9775 339.111C36.4564 332.251 42.3685 323.968 49.6192 318.036C57.784 311.356 62.0251 301.016 68.4115 293.033C75.5405 284.121 84.4559 277.323 91.1321 267.976C97.9143 258.481 103.563 248.502 109.818 238.567C116.139 228.527 121.639 217.491 128.823 208.255C136.627 198.221 139.211 184.56 144.642 173.378C150.271 161.789 152.207 150.286 156.587 138.023C160.994 125.683 160.887 109.957 160.887 96.9343C160.887 83.6923 163.523 67.2219 159.878 54.4658C157.779 47.118 154.052 33.4689 146.554 29.3032C141.737 26.6272 140.534 22.402 134.503 22.402C128.97 22.402 124.107 21.4465 118.365 21.4465C113.212 21.4465 107.257 20.816 102.599 23.1452C99.6537 24.6177 90.6668 26.1251 90.1766 30.0464C90.0024 31.4399 85.9855 35.478 84.868 36.7352C83.4119 38.3734 83.109 41.0482 82.0545 42.9462C79.498 47.5479 78.7101 53.957 78.7101 59.1904C78.7101 63.5328 76.799 68.4458 76.799 73.2581C76.799 79.5677 75.8435 86.3159 75.8435 92.8998C75.8435 98.8237 75.2658 104.681 77.2768 110.312C78.9512 115 78.6364 120.69 81.099 125.123C82.9508 128.456 84.022 136.141 84.4434 139.934C85.0527 145.418 86.7408 151.449 88.7433 156.656C91.1483 162.909 92.9323 169.73 94.742 176.244C95.9925 180.746 97.0451 185.488 97.874 190.047C98.516 193.577 100.558 197.796 100.688 201.301C100.773 203.604 101.046 207.941 102.599 209.688C104.786 212.149 104.301 217.421 105.465 220.624C107.977 227.532 108.244 235.242 110.721 242.177C115.71 256.147 115.872 271.911 120.542 285.919C121.533 288.892 121.066 292.431 122.134 295.368C123.892 300.202 124.802 305.413 125.585 310.498C126.628 317.277 128.91 324.03 130.362 330.564C131.42 335.322 132.927 340.993 134.875 345.375C135.45 346.669 134.893 348.262 135.14 349.622C135.426 351.193 136.998 351.722 136.998 353.497C136.998 355.477 138.797 357.799 138.909 359.708C139.144 363.705 140.73 355.072 140.82 353.763C141.148 349.007 143.066 344.358 143.74 339.642C144.601 333.613 145.523 326.095 147.934 320.584C150.449 314.835 149.855 307.369 152.499 301.42C154.396 297.151 156.072 292.673 159.188 289.211C166.779 280.776 174.427 271.285 185.253 268.879C191.304 267.534 195.559 266.065 201.975 266.065C206.147 266.065 211.659 267.899 214.397 271.321C216.519 273.973 218.692 277.439 221.086 279.974C225.396 284.537 226.305 291.827 229.155 297.12C231.081 300.697 232.635 307.286 233.561 311.453C234.985 317.862 239.479 324.584 240.197 331.042C240.833 336.765 245.582 341.371 245.93 347.286C246.104 350.24 252.018 355.467 254.53 356.364C261.125 358.719 268.355 358.753 275.605 358.753C280.46 358.753 284.33 357.671 289.035 356.789C295.751 355.529 299.158 352.712 304.43 349.197C311.1 344.751 317.909 339.045 322.692 332.422C331.808 319.8 335.97 298.898 331.929 283.743C330.823 279.598 327.656 272.954 323.541 271.055C319.025 268.971 316.284 262.532 312.605 259.589C309.105 256.788 299.694 260.845 297.104 263.199C289.303 270.29 283.131 277.261 280.542 287.618C279.19 293.026 280.329 300.19 280.329 305.773C280.329 310.887 284.517 315.841 286.063 320.478C291.26 336.071 311.449 346.134 325.452 351.321C331.613 353.603 339.104 354.931 345.731 354.931C350.539 354.931 355.364 355.064 360.17 354.931C362.977 354.853 366.142 352.447 369.195 352.011C373.168 351.443 377.048 348.562 380.555 346.809C382.935 345.619 387.327 342.701 389.049 340.597C392.906 335.883 399.678 332.18 404.444 328.282C408.994 324.558 413.31 320.597 416.972 315.966C420.405 311.623 424.077 307.454 427.483 303.119C432.308 296.978 434.888 289.043 438.896 282.363C447.196 268.529 449.086 251.684 456.202 237.452C463.402 223.052 466.958 205.496 470.482 189.834C475.397 167.989 479.163 146.521 481.736 124.327C483.177 111.901 482.904 98.8199 482.904 86.3172C482.904 77.0256 483.656 67.3868 482.851 58.1287C482.117 49.68 479.657 41.4004 475.738 34.3463C469.716 23.5077 461.439 17.9939 448.505 17.6243C440.896 17.4069 429.349 25.2536 425.625 31.9575C423.496 35.7903 420.324 38.7311 418.671 42.8931C416.368 48.6897 414.137 54.6995 412.194 60.6237C409.152 69.903 407.538 81.053 406.514 90.7764C405.735 98.1768 406.461 106.118 406.461 113.55C406.461 122.38 409.064 130.495 409.328 139.19C409.651 149.872 412.027 161.329 413.628 171.891C415.315 183.031 417.98 194.29 419.095 205.441C420.076 215.252 422.3 224.594 423.873 234.32C424.867 240.467 426.913 246.351 428.438 252.369C430.286 259.657 431.234 267.451 433.694 274.559C438.996 289.877 443.899 305.721 452.805 319.576C458.425 328.318 464.138 335.783 472.606 342.296C477.02 345.692 488.304 343.464 493.628 343.464C504.928 343.464 514.887 343.455 525.373 339.642C530.904 337.631 536.649 335.331 541.829 332.528C546.267 330.128 550.901 328.184 555.366 325.84C558.795 324.039 560.891 321.272 564.072 319.204C586.559 304.588 601.931 279.427 612.964 255.554C621.067 238.023 626.663 219.679 630.111 200.717C633.077 184.404 639.04 168.566 640.356 151.931C642.592 123.68 642.48 95.4235 642.48 67.1002C642.48 51.2087 638.496 37.9886 633.88 22.986C631.894 16.5309 626.392 11.211 620.078 9.76766C615.804 8.79084 611.487 9.02446 607.125 9.02446C599.001 9.02446 595.502 11.024 589.023 15.2355C577.911 22.4578 573.712 35.0271 571.557 47.5646C568.252 66.7966 566.992 85.8073 566.992 105.428C566.992 113.306 565.687 122.41 567.204 130.166C568.809 138.37 570.635 146.61 572.194 154.851C576.162 175.822 579.622 196.984 582.705 218.129C586.712 245.607 590.851 272.746 600.436 298.978C603.536 307.462 607.562 314.421 611.902 322.018C617.051 331.027 628.203 332.997 637.649 334.386C651.484 336.421 665.489 335.82 679.534 335.82C694.573 335.82 708.123 335.365 722.533 331.042C733.569 327.731 747.44 326.413 755.711 318.142C762.131 311.723 764.066 299.606 765.745 291.015C767.741 280.797 767.166 269.123 766.647 258.739C766.181 249.407 764.36 239.975 762.825 230.763C762.099 226.41 760.212 222.365 759.268 218.076C758.268 213.531 757.685 207.897 755.021 203.955C749.759 196.167 748.115 188.839 737.822 186.968C729.352 185.428 719.102 186.471 710.695 187.764C707.282 188.289 700.623 191.398 698.114 193.656C696.635 194.987 695.131 198.273 693.548 199.177C690.404 200.974 686.908 211.641 686.488 215.422C685.811 221.515 685.479 227.401 685.479 233.577C685.479 244.949 687.983 253.814 692.38 264.366C696.253 273.66 698.954 284.094 707.404 290.432C715.068 296.18 724.32 298.721 732.938 302.907C736.833 304.798 738.862 305.727 743.289 304.765C750.907 303.109 760.011 300.071 760.011 290.909C760.011 286.95 762.093 283.317 762.878 279.39C764.258 272.489 763.4 265.547 764.577 258.633C766.789 245.635 764.619 230.865 763.781 217.863C763.256 209.734 758.375 202.318 752.845 196.788C749.517 193.46 744.998 194.329 740.954 192.913C736.717 191.43 726.962 189.513 722.745 191.745C719.101 193.675 720.211 200.767 719.401 204.008C718.336 208.27 719.267 211.67 720.834 215.634C724.263 224.307 728.141 230.975 732.885 238.355C737.58 245.659 746.204 250.069 753.323 254.599C763.676 261.188 778.924 259.192 790.589 260.544C794.991 261.055 798.982 261.288 803.436 261.288C806.87 261.288 810.14 259.376 813.522 259.376"
    )
}

private val colors = listOf(
    Color(0xFF3FCEBC),
    Color(0xFF3CBCEB),
    Color(0xFF5F96E7),
    Color(0xFF816FE3),
    Color(0xFF9F5EE2),
    Color(0xFFBD4CE0),
    Color(0xFFDE589F),
    Color(0xFFFF645E),
    Color(0xFFFDA859),
    Color(0xFFFAEC54),
    Color(0xFF9EE671),
    Color(0xFF67E282),
    Color(0xFF3FCEBC)
)