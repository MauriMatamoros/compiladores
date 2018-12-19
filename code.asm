.data
	y:	.word	0
	x:	.word	0
	z:	.word	0
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

	addi	$t2,	$zero,	0
	sw	$t2,	x
Label1:
	lw	$t1,	x
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
	move	$sp,	$fp
	lw	$fp,	-4($sp)
	lw	$ra,	-8($sp)
	jr	$ra

