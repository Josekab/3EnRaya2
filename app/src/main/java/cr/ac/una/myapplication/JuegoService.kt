package cr.ac.una.myapplication

class JuegoService  {
    val matriz = Array(3) { CharArray(3) }
    var figura: Char = 'X'

    /**
     * @return X gana, O gana, Empate, Sigue X, Sigue O
     */
    fun jugada(x:Int, y:Int): Pair<String, String> {
        matriz[x][y] = figura

        // Verificar si hay un ganador
        val ganador = hayGanador()
        if (ganador.first) {
            return Pair("${figura} gana", ganador.second)
        }
        // Verificar si hay un empate
        if (hayEmpate()) {
            return Pair("Empate", "")
        }
        if (figura == 'X') figura = 'O' else figura = 'X'
        return Pair("Turno de $figura", "")
    }

    private fun hayGanador(): Pair<Boolean, String> {
        // Verificar filas y columnas
        for (i in 0 until 3) {
            if (matriz[i][0] != ' ' && matriz[i][0] == matriz[i][1] && matriz[i][1] == matriz[i][2]) {
                return Pair(true, "row$i")
            }
            if (matriz[0][i] != ' ' && matriz[0][i] == matriz[1][i] && matriz[1][i] == matriz[2][i]) {
                return Pair(true, "col$i")
            }
        }

        // Verificar diagonales
        if (matriz[0][0] != ' ' && matriz[0][0] == matriz[1][1] && matriz[1][1] == matriz[2][2]) {
            return Pair(true, "diag0")
        }
        if (matriz[0][2] != ' ' && matriz[0][2] == matriz[1][1] && matriz[1][1] == matriz[2][0]) {
            return Pair(true, "diag1")
        }

        return Pair(false, "")
    }

    private fun hayEmpate(): Boolean {
        // Lógica para verificar si hay un empate
        for (i in 0 until 3) {
            for (j in 0 until 3) {
                if (matriz[i][j] == ' ') {
                    return false
                }
            }
        }
        // Si llegamos aquí y no hay un ganador, es un empate
        return true
    }

    fun inicializar() {
        matriz[0][0] = ' '
        matriz[0][1] = ' '
        matriz[0][2] = ' '
        matriz[1][0] = ' '
        matriz[1][1] = ' '
        matriz[1][2] = ' '
        matriz[2][0] = ' '
        matriz[2][1] = ' '
        matriz[2][2] = ' '
    }
}
