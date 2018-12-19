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

	addi	$t1,	$zero,	0
	beq	$0,	$t1,	Label1
	addi	$t2,	$zero,	5
	sw	$t2,	x
	lw	$t2,	x
	li	$v0,	1
	move	$a0,	$t2
	syscall
	b	Label2
Label1:
	addi	$t2,	$zero,	1
	beq	$0,	$t2,	Label3
	addi	$t3,	$zero,	4
	sw	$t3,	x
	lw	$t3,	x
	li	$v0,	1
	move	$a0,	$t3
	syscall
	b	Label4
Label3:
	addi	$t3,	$zero,	3
	sw	$t3,	x
	lw	$t3,	x
	li	$v0,	1
	move	$a0,	$t3
	syscall
Label4:
Label2:
	move	$sp,	$fp
	lw	$fp,	-4($sp)
	lw	$ra,	-8($sp)
	jr	$ra

