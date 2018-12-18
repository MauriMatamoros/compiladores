.data
	x:	.word	0
	y:	.word	0
.text
Main:
	addi	$t1,	$zero,	7
	sw	$t1,	y
	addi	$t1,	$zero,	2
	addi	$t2,	$zero,	4
	addi	$t3,	$zero,	2
	div	$t2,	$t2,	$t3
	add	$t1,	$t1,	$t2
	addi	$t2,	$zero,	4
	mul	$t1,	$t1,	$t2
	sw	$t1,	x
	addi	$t1,	$zero,	1
	blt	$0,	$t1,	Label4
	b	Label5
Label4:
	lw	$t2,	x
	li	$v0,	1
	move	$a0,	$t2
	syscall
	b	Label6
Label5:
	lw	$t2,	y
	li	$v0,	1
	move	$a0,	$t2
	syscall
Label6:
Error unkown operator: return

