.data
	y:	.word	0
	x:	.word	0
.text
	jal	Main
	li	$v0,	10
	syscall
Hello:	sw	$fp,	-4($sp)
	sw	$ra,	-8($sp)
	move	$fp,	$sp
	subi	$sp,	$sp,	12

	addi	$t1,	$zero,	7
	sw	$t1,	y
	lw	$t1,	y
	li	$v0,	1
	move	$a0,	$t1
	syscall
	move	$sp,	$fp
	lw	$fp,	-4($sp)
	lw	$ra,	-8($sp)
	jr	$ra
Main:	sw	$fp,	-4($sp)
	sw	$ra,	-8($sp)
	move	$fp,	$sp
	subi	$sp,	$sp,	12

	li	$v0,	5
	syscall
	sw	$v0,	x
	lw	$t1,	x
	li	$v0,	1
	move	$a0,	$t1
	syscall
	jal	Hello
	lw	$t1,	x
	addi	$t2,	$zero,	1000
	add	$t1,	$t1,	$t2
	sw	$t1,	x
	lw	$t1,	x
	li	$v0,	1
	move	$a0,	$t1
	syscall
	move	$sp,	$fp
	lw	$fp,	-4($sp)
	lw	$ra,	-8($sp)
	jr	$ra

