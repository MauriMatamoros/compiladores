.data
	x:	.word	0
.text
Main:
	addi	$t1,	$zero,	4
	sw	$t1,	x
	lw	$t1,	x
Error unkown operator: write
Error unkown operator: return

