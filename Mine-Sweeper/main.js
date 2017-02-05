var TABLE_HEIGHT = 15 //15
var TABLE_WIDTH = 30 // 30
var BOMB = -1
var BOMB_COUNT = 70 // 70
var BOMB_IMG = "<img src = \"images/mine.png\" class=\"icons\">"
var FLAG_IMG = "<img src = \"images/flag.png\" class=\"icons\">"
var RECTANGLE_IMG = "<img src = \"images/rectangle.png\" class=\"icons\">"
var MARQUEE = "<marquee direction=\"right\" scrollamount=\"80\">" +
    "<img src = \"images/glasses.png\" id=\"glasses\"></marquee>"
var game_over = false
var game_started = false

var grid = new Array(TABLE_WIDTH)
for (var i = 0; i < TABLE_WIDTH; i++)
    grid[i] = new Array(TABLE_HEIGHT)


for (var i = 0; i < TABLE_WIDTH; i++)
    for (var j = 0; j < TABLE_HEIGHT; j++)
        grid[i][j] = { value: 0, visible: false, flag: false }


if (BOMB_COUNT > TABLE_HEIGHT * TABLE_WIDTH - 9)
    BOMB_COUNT = TABLE_HEIGHT * TABLE_WIDTH - 9

function initialize_table() {
    var table_html = generate_table(TABLE_HEIGHT, TABLE_WIDTH)
    $("#game_table").html(table_html)
}
$(document).ready(initialize_table)

function generate_table(height, width) {
    var html = ""
    for (var h = 0; h < height; h++) {
        html += "<tr>\n"
        for (var w = 0; w < width; w++) {
            html += "<td id = \"x" + w + "y" + h + "\""
            html += "onclick=\"square_click(" + w + "," + h + ")\" "
            html += "oncontextmenu=\"square_right_click(" + w + "," + h + "); return false;\" "
            html += "onmouseover=\"square_hover(" + w + "," + h + "); \" >"
            html += "</td>\n"

        }
        html += "</tr>"
    }

    return html

}

function square_hover(x, y) {

    if (grid[x][y].visible || grid[x][y].flag || game_over)
        $("#x" + x + "y" + y).css('cursor', 'default');
    else
        $("#x" + x + "y" + y).css('cursor', 'pointer');
}

function get_neighbors(x, y) {
    var neighbors = []
    var top = y > 0
    var buttom = y < TABLE_HEIGHT - 1
    var left = x > 0
    var right = x < TABLE_WIDTH - 1
    if (top)
        neighbors.push({ x: x, y: y - 1 })
    if (buttom)
        neighbors.push({ x: x, y: y + 1 })
    if (left)
        neighbors.push({ x: x - 1, y: y })
    if (right)
        neighbors.push({ x: x + 1, y: y })
    if (top && right)
        neighbors.push({ x: x + 1, y: y - 1 })
    if (top && left)
        neighbors.push({ x: x - 1, y: y - 1 })
    if (buttom && right)
        neighbors.push({ x: x + 1, y: y + 1 })
    if (buttom && left)
        neighbors.push({ x: x - 1, y: y + 1 })

    return neighbors
}

function initialize_grid(x, y) // gets the first square clicked
{


    grid[x][y].visible = true

    generate_bombs(x, y)


    for (var i = 0; i < TABLE_WIDTH; i++)
        for (var j = 0; j < TABLE_HEIGHT; j++) {
            if (grid[i][j].value != BOMB)
                grid[i][j].value = neighbors_bomb_count(i, j)

        }

}

function neighbors_bomb_count(x, y) {

    var count = 0
    var neighbors = get_neighbors(x, y)
    var n
    for (var i = 0; i < neighbors.length; i++) {
        n = neighbors[i]
        if (grid[n.x][n.y].value == BOMB)
            count++
    }
    return count
}

function click_neighbors(x, y) {
    var neighbors = get_neighbors(x, y)
    for (var i = 0; i < neighbors.length; i++)
        square_click(neighbors[i].x, neighbors[i].y)

}

function generate_bombs(x, y) //gets the first square clicked
{
    var safe_squares
    var is_a_no_bomb_square
    var w, h
    no_bomb_squares = get_neighbors(x, y)
    no_bomb_squares.push({ x: x, y: y })

    for (var i = 0; i < BOMB_COUNT; i++) {
        do {
            is_a_no_bomb_square = false
            w = Math.floor(Math.random() * TABLE_WIDTH),
                h = Math.floor(Math.random() * TABLE_HEIGHT)
            for (var j = 0; j < no_bomb_squares.length; j++)
                if (no_bomb_squares[j].x == w && no_bomb_squares[j].y == h) {
                    is_a_no_bomb_square = true
                    break
                }

        }
        while (is_a_no_bomb_square)
        grid[w][h].value = BOMB
        no_bomb_squares.push({ x: w, y: h })
    }
}

function update_table() {
    var square
    for (var x = 0; x < TABLE_WIDTH; x++)
        for (var y = 0; y < TABLE_HEIGHT; y++) {
            square = grid[x][y]
            if (square.flag)
                $("#x" + x + "y" + y).html(FLAG_IMG)

            else if (square.visible == true) {
                if (square.value == BOMB)
                    $("#x" + x + "y" + y).html(BOMB_IMG)
                else if (square.value == 0)
                    $("#x" + x + "y" + y).html(RECTANGLE_IMG)
                else $("#x" + x + "y" + y).html(square.value)
            } else $("#x" + x + "y" + y).html("")
        }
}

function square_click(x, y) {
    var square = grid[x][y]


    if (game_over || square.flag || square.visible)
        return

    square.visible = true

    if (!game_started) {

        game_started = true
        initialize_grid(x, y)

    } else if (square.value == BOMB)
        end_game()

    update_table()
    if (square.value == 0)
        click_neighbors(x, y)
    check_win()

}

function check_win() {
    var square
    for (var i = 0; i < TABLE_WIDTH; i++)
        for (var j = 0; j < TABLE_HEIGHT; j++) {
            square = grid[i][j]
            if ((!square.visible && !square.flag) ||
                (square.flag && square.value != BOMB) ||
                (!square.flag && square.value == BOMB))

                return

        }

    win()
}

function win() {

    $("#marquee").html(MARQUEE)
}

function square_right_click(x, y) {

    if (game_over || grid[x][y].visible)
        return

    grid[x][y].flag = !grid[x][y].flag
    update_table()
    check_win()
}

function end_game() {
    game_over = true
    for (var x = 0; x < TABLE_WIDTH; x++)
        for (var y = 0; y < TABLE_HEIGHT; y++) {
            if (grid[x][y].value == BOMB)
                grid[x][y].visible = true


        }
}

function restart() {
    $("#marquee").html("")
    game_over = false
    game_started = false
    for (var i = 0; i < TABLE_WIDTH; i++)
        for (var j = 0; j < TABLE_HEIGHT; j++)
            grid[i][j] = { value: 0, visible: false, flag: false }
    initialize_table()
}