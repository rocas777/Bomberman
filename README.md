# lpoo-2020-g81
lpoo-2020-g81 created by GitHub Classroom

# Bomberman Versão Fixe

## Descrição
Objetivo do jogo é encontrar uma porta escondida "por baixo" de um dos blocos destrutiveis.
Estes blocos podem ser destruidos com uma bomba que o player vai ter (munição infinita) e explode na horizontal e vertical num range de 4 blocos.
O mapa será sempre o mesmo na medida em que os blocos indestrutiveis estarão sempre no mesmo sítio. No entanto os outros serão colocados de forma aleatória, tal como o jogador, porta e monstros.
Haverão também monstros que se entram em contacto com o player, este perderá uma vida. Se as vidas chegarem ao 0, obviamente, o player perde.
Para além disso existirão moedas (que podem ou não estar escondidas como a porta) que o player pode apanhar para aumentar o score.

## Funcionalidades:
1. Existirão 3 dificuldades diferentes, que vão modificar o movimento dos monstros e número de blocos destrutiveis.
1. Niveis, cada um com tempo limite. Com o passar dos mesmo, a dificuldade do jogo aumentará.
1. O player, se apanhado na explosão da propria bomba perde 1 vida.

## Design Patterns
1. Observer: 
	- o campo atualizar-se-á a um determinado framerate o que notificará cada monstro para se mexer.
	- receberá os inputs do teclado para atualizar o hero.

1. Strategy:	
	- implementar as diferentes dificuldades.

1. Commands.Command: 	
	- registar todas as ações como comandos.

## To do (maybe, se tivermos tempo):
1. Instance of
1. Fechar o jogo on 'X'



