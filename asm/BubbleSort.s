	.text
	.global	main
main:
	addi	sp,sp,-4
	sw	ra,0(sp)
	li	s10,24
	jal	_hallocate
	mv	s0,s10
	la	s1,BBS_Start
	sw	s1,8(s0)
	la	s1,BBS_Sort
	sw	s1,12(s0)
	la	s1,BBS_Print
	sw	s1,16(s0)
	la	s1,BBS_Init
	sw	s1,20(s0)
	mv	s2,s0
	lw	s1,8(s2)
	li	s0,10
	mv	a0,s2
	mv	a1,s0
	jalr	s1
	mv	s0,s10
	mv	s10,s0
	jal	_print
	lw	ra,0(sp)
	addi	sp,sp,4
	jr	ra

	.text
	.global	BBS_Start
BBS_Start:
	addi	sp,sp,-20
	sw	ra,16(sp)
	sw	s0,12(sp)
	sw	s1,8(sp)
	sw	s2,4(sp)
	sw	s3,0(sp)
	mv	s0,a0
	mv	s1,a1
	mv	s3,s0
	lw	s2,20(s3)
	mv	a0,s3
	mv	a1,s1
	jalr	s2
	mv	s1,s10
	mv	s2,s0
	lw	s1,16(s2)
	mv	a0,s2
	jalr	s1
	mv	s1,s10
	li	s10,99999
	jal	_print
	mv	s2,s0
	lw	s1,12(s2)
	mv	a0,s2
	jalr	s1
	mv	s1,s10
	mv	s1,s0
	lw	s0,16(s1)
	mv	a0,s1
	jalr	s0
	mv	s0,s10
	li	s10,0
	lw	s0,12(sp)
	lw	s1,8(sp)
	lw	s2,4(sp)
	lw	s3,0(sp)
	lw	ra,16(sp)
	addi	sp,sp,20
	jr	ra

	.text
	.global	BBS_Sort
BBS_Sort:
	addi	sp,sp,-36
	sw	ra,32(sp)
	sw	s0,28(sp)
	sw	s1,24(sp)
	sw	s2,20(sp)
	sw	s3,16(sp)
	sw	s4,12(sp)
	sw	s5,8(sp)
	sw	s6,4(sp)
	sw	s7,0(sp)
	mv	s0,a0
	lw	s1,4(s0)
	addi	s1,s1,-1
	li	s2,0
	addi	s2,s2,-1
L4:	slt	s3,s2,s1
	beqz	s3,L5
	li	s3,1
L6:	addi	s4,s1,1
	slt	s4,s3,s4
	beqz	s4,L7
	addi	s5,s3,-1
	lw	s4,0(s0)
	li	s10,4
	mul	s5,s5,s10
	add	s4,s4,s5
	lw	s4,0(s4)
	mv	s4,s4
	lw	s6,0(s0)
	li	s10,4
	mul	s5,s3,s10
	add	s5,s6,s5
	lw	s5,0(s5)
	mv	s5,s5
	slt	s4,s5,s4
	beqz	s4,L8
	addi	s5,s3,-1
	lw	s6,0(s0)
	li	s10,4
	mul	s4,s5,s10
	add	s4,s6,s4
	lw	s4,0(s4)
	mv	s4,s4
	lw	s6,0(s0)
	li	s10,4
	mul	s5,s5,s10
	add	s5,s6,s5
	lw	s7,0(s0)
	li	s10,4
	mul	s6,s3,s10
	add	s6,s7,s6
	lw	s6,0(s6)
	sw	s6,0(s5)
	lw	s6,0(s0)
	li	s10,4
	mul	s5,s3,s10
	add	s5,s6,s5
	sw	s4,0(s5)
	j	L9
L8:	li	s4,0
L9:	addi	zero,zero,0
	addi	s3,s3,1
	j	L6
L7:	addi	zero,zero,0
	addi	s1,s1,-1
	j	L4
L5:	addi	zero,zero,0
	li	s10,0
	lw	s0,28(sp)
	lw	s1,24(sp)
	lw	s2,20(sp)
	lw	s3,16(sp)
	lw	s4,12(sp)
	lw	s5,8(sp)
	lw	s6,4(sp)
	lw	s7,0(sp)
	lw	ra,32(sp)
	addi	sp,sp,36
	jr	ra

	.text
	.global	BBS_Print
BBS_Print:
	addi	sp,sp,-20
	sw	ra,16(sp)
	sw	s0,12(sp)
	sw	s1,8(sp)
	sw	s2,4(sp)
	sw	s3,0(sp)
	mv	s0,a0
	li	s1,0
L10:	lw	s2,4(s0)
	slt	s2,s1,s2
	beqz	s2,L11
	lw	s3,0(s0)
	li	s10,4
	mul	s2,s1,s10
	add	s2,s3,s2
	lw	s2,0(s2)
	mv	s10,s2
	jal	_print
	addi	s1,s1,1
	j	L10
L11:	addi	zero,zero,0
	li	s10,0
	lw	s0,12(sp)
	lw	s1,8(sp)
	lw	s2,4(sp)
	lw	s3,0(sp)
	lw	ra,16(sp)
	addi	sp,sp,20
	jr	ra

	.text
	.global	BBS_Init
BBS_Init:
	addi	sp,sp,-16
	sw	ra,12(sp)
	sw	s0,8(sp)
	sw	s1,4(sp)
	sw	s2,0(sp)
	mv	s0,a0
	mv	s1,a1
	sw	s1,4(s0)
	mv	s1,s1
	addi	s2,s1,1
	li	s10,4
	mul	s2,s2,s10
	mv	s10,s2
	jal	_hallocate
	mv	s2,s10
	sw	s1,0(s2)
	li	s1,4
	add	s1,s1,s2
	sw	s1,0(s0)
	lw	s1,0(s0)
	li	s2,0
	li	s10,4
	mul	s2,s2,s10
	add	s2,s1,s2
	li	s1,20
	sw	s1,0(s2)
	lw	s1,0(s0)
	li	s2,1
	li	s10,4
	mul	s2,s2,s10
	add	s2,s1,s2
	li	s1,7
	sw	s1,0(s2)
	lw	s1,0(s0)
	li	s2,2
	li	s10,4
	mul	s2,s2,s10
	add	s2,s1,s2
	li	s1,12
	sw	s1,0(s2)
	lw	s1,0(s0)
	li	s2,3
	li	s10,4
	mul	s2,s2,s10
	add	s2,s1,s2
	li	s1,18
	sw	s1,0(s2)
	lw	s1,0(s0)
	li	s2,4
	li	s10,4
	mul	s2,s2,s10
	add	s2,s1,s2
	li	s1,2
	sw	s1,0(s2)
	lw	s1,0(s0)
	li	s2,5
	li	s10,4
	mul	s2,s2,s10
	add	s2,s1,s2
	li	s1,11
	sw	s1,0(s2)
	lw	s1,0(s0)
	li	s2,6
	li	s10,4
	mul	s2,s2,s10
	add	s2,s1,s2
	li	s1,6
	sw	s1,0(s2)
	lw	s1,0(s0)
	li	s2,7
	li	s10,4
	mul	s2,s2,s10
	add	s2,s1,s2
	li	s1,9
	sw	s1,0(s2)
	lw	s1,0(s0)
	li	s2,8
	li	s10,4
	mul	s2,s2,s10
	add	s2,s1,s2
	li	s1,19
	sw	s1,0(s2)
	lw	s0,0(s0)
	li	s1,9
	li	s10,4
	mul	s1,s1,s10
	add	s1,s0,s1
	li	s0,5
	sw	s0,0(s1)
	li	s10,0
	lw	s0,8(sp)
	lw	s1,4(sp)
	lw	s2,0(sp)
	lw	ra,12(sp)
	addi	sp,sp,16
	jr	ra

	.section	.rodata
	.align	3
.LC0:
	.string	"0"
	.text
	.align	2
	.globl	_print
	.type	_print, @function
_print:
	add	sp,sp,-80
	sd	ra,72(sp)
	sd	s0,64(sp)
	add	s0,sp,80
	sw	s10,-68(s0)
	lw	a5,-68(s0)
	bnez	a5,.L4
	lui	a5,%hi(.LC0)
	add	a0,a5,%lo(.LC0)
	call	puts
	j	.L11
.L4:
	lw	a5,-68(s0)
	bgez	a5,.L6
	li	a0,45
	call	putchar
	lw	a5,-68(s0)
	subw	a5,zero,a5
	sw	a5,-68(s0)
.L6:
	sw	zero,-20(s0)
	j	.L7
.L8:
	lw	a4,-68(s0)
	li	a5,10
	remw	a5,a4,a5
	and	a4,a5,0xff
	lw	a5,-20(s0)
	add	a3,s0,-16
	add	a5,a3,a5
	sb	a4,-24(a5)
	lw	a5,-20(s0)
	addw	a5,a5,1
	sw	a5,-20(s0)
	lw	a4,-68(s0)
	li	a5,10
	divw	a5,a4,a5
	sw	a5,-68(s0)
.L7:
	lw	a5,-68(s0)
	bnez	a5,.L8
	sw	zero,-24(s0)
	j	.L9
.L10:
	lw	a5,-20(s0)
	addw	a4,a5,-1
	lw	a5,-24(s0)
	subw	a5,a4,a5
	add	a4,s0,-16
	add	a5,a4,a5
	lbu	a5,-24(a5)
	addw	a5,a5,48
	and	a4,a5,0xff
	lw	a5,-24(s0)
	add	a3,s0,-16
	add	a5,a3,a5
	sb	a4,-40(a5)
	lw	a5,-24(s0)
	addw	a5,a5,1
	sw	a5,-24(s0)
.L9:
	lw	a4,-24(s0)
	lw	a5,-20(s0)
	blt	a4,a5,.L10
	lw	a5,-20(s0)
	add	a4,s0,-16
	add	a5,a4,a5
	sb	zero,-40(a5)
	add	a5,s0,-56
	mv	a0,a5
	call	puts
.L11:
	nop
	ld	ra,72(sp)
	ld	s0,64(sp)
	add	sp,sp,80
	jr	ra
	.size	_print, .-_print
	.align	2
	.globl	_hallocate
	.type	_hallocate, @function
_hallocate:
	add	sp,sp,-32
	sd	ra,24(sp)
	sd	s0,16(sp)
	add	s0,sp,32
	sw	s10,-20(s0)
	mv	a0,s10
	call	malloc
	mv	s10,a0
	ld	ra,24(sp)
	ld	s0,16(sp)
	add	sp,sp,32
	jr	ra
	.size	_hallocate, .-_hallocate

