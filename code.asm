.data
	x:	.word	0
	y:	.word	0
.text
Main:
	addi	$t1,	$zero,	4
	sw	$t1,	y
	addi	$t1,	$zero,	0
	sw	$t1,	x
	addi	$t2,	$zero,	1
	sw	$t2,	x
	lw	$t1,	x
Label1:
	slti	$t2,	$t1,	9
	beq	$0,	$t2,	Label2
	lw	$t3,	x
	li	$v0,	1
	move	$a0,	$t3
	syscall
	addi	$t1,	$t1,	1
	sw	$t1,	x
	b	Label1
Label2:
Error unkown operator: return

