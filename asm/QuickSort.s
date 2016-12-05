	.text
	.global	main
main:
	addi	sp,sp,-4
	sw	ra,0(sp)
	li	s10,24
	jal	_hallocate
	mv	s0,s10
	la	s1,QS_Start
	sw	s1,8(s0)
	la	s1,QS_Sort
	sw	s1,12(s0)
	la	s1,QS_Print
	sw	s1,16(s0)
	la	s1,QS_Init
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
	.global	QS_Start
QS_Start:
	addi	sp,sp,-24
	sw	ra,20(sp)
	sw	s0,16(sp)
	sw	s1,12(sp)
	sw	s2,8(sp)
	sw	s3,4(sp)
	sw	s4,0(sp)
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
	li	s10,9999
	jal	_print
	lw	s1,4(s0)
	addi	s4,s1,-1
	mv	s3,s0
	lw	s2,12(s3)
	li	s1,0
	mv	a0,s3
	mv	a1,s1
	mv	a2,s4
	jalr	s2
	mv	s1,s10
	mv	s1,s0
	lw	s0,16(s1)
	mv	a0,s1
	jalr	s0
	mv	s0,s10
	li	s10,0
	lw	s0,16(sp)
	lw	s1,12(sp)
	lw	s2,8(sp)
	lw	s3,4(sp)
	lw	s4,0(sp)
	lw	ra,20(sp)
	addi	sp,sp,24
	jr	ra

	.text
	.global	QS_Sort
QS_Sort:
	addi	sp,sp,-44
	sw	ra,40(sp)
	sw	s0,36(sp)
	sw	s1,32(sp)
	sw	s2,28(sp)
	sw	s3,24(sp)
	sw	s4,20(sp)
	sw	s5,16(sp)
	sw	s6,12(sp)
	sw	s7,8(sp)
	sw	s8,4(sp)
	sw	s9,0(sp)
	mv	s1,a0
	mv	s2,a1
	mv	s0,a2
	li	s4,0
	slt	s3,s2,s0
	beqz	s3,L4
	lw	s5,0(s1)
	li	s10,4
	mul	s3,s0,s10
	add	s3,s5,s3
	lw	s3,0(s3)
	mv	s6,s3
	addi	s3,s2,-1
	mv	s5,s0
	li	s7,1
L5:	beqz	s7,L6
	li	s4,1
L7:	beqz	s4,L8
	addi	s3,s3,1
	lw	s7,0(s1)
	li	s10,4
	mul	s4,s3,s10
	add	s4,s7,s4
	lw	s4,0(s4)
	mv	s4,s4
	slt	s4,s4,s6
	slti	s4,s4,1
	beqz	s4,L9
	li	s4,0
	j	L10
L9:	li	s4,1
L10:	addi	zero,zero,0
	j	L7
L8:	addi	zero,zero,0
	li	s4,1
L11:	beqz	s4,L12
	addi	s5,s5,-1
	lw	s7,0(s1)
	li	s10,4
	mul	s4,s5,s10
	add	s4,s7,s4
	lw	s4,0(s4)
	mv	s4,s4
	slt	s4,s6,s4
	slti	s4,s4,1
	beqz	s4,L13
	li	s4,0
	j	L14
L13:	li	s4,1
L14:	addi	zero,zero,0
	j	L11
L12:	addi	zero,zero,0
	lw	s7,0(s1)
	li	s10,4
	mul	s4,s3,s10
	add	s4,s7,s4
	lw	s4,0(s4)
	mv	s4,s4
	lw	s8,0(s1)
	li	s10,4
	mul	s7,s3,s10
	add	s7,s8,s7
	lw	s9,0(s1)
	li	s10,4
	mul	s8,s5,s10
	add	s8,s9,s8
	lw	s8,0(s8)
	sw	s8,0(s7)
	lw	s8,0(s1)
	li	s10,4
	mul	s7,s5,s10
	add	s7,s8,s7
	sw	s4,0(s7)
	addi	s7,s3,1
	slt	s7,s5,s7
	beqz	s7,L15
	li	s7,0
	j	L16
L15:	li	s7,1
L16:	addi	zero,zero,0
	j	L5
L6:	addi	zero,zero,0
	lw	s6,0(s1)
	li	s10,4
	mul	s5,s5,s10
	add	s5,s6,s5
	lw	s7,0(s1)
	li	s10,4
	mul	s6,s3,s10
	add	s6,s7,s6
	lw	s6,0(s6)
	sw	s6,0(s5)
	lw	s6,0(s1)
	li	s10,4
	mul	s5,s3,s10
	add	s5,s6,s5
	lw	s7,0(s1)
	li	s10,4
	mul	s6,s0,s10
	add	s6,s7,s6
	lw	s6,0(s6)
	sw	s6,0(s5)
	lw	s6,0(s1)
	li	s10,4
	mul	s5,s0,s10
	add	s5,s6,s5
	sw	s4,0(s5)
	mv	s6,s1
	lw	s5,12(s6)
	addi	s4,s3,-1
	mv	a0,s6
	mv	a1,s2
	mv	a2,s4
	jalr	s5
	mv	s2,s10
	mv	s2,s1
	lw	s1,12(s2)
	addi	s3,s3,1
	mv	a0,s2
	mv	a1,s3
	mv	a2,s0
	jalr	s1
	mv	s0,s10
	j	L17
L4:	li	s0,0
L17:	addi	zero,zero,0
	li	s10,0
	lw	s0,36(sp)
	lw	s1,32(sp)
	lw	s2,28(sp)
	lw	s3,24(sp)
	lw	s4,20(sp)
	lw	s5,16(sp)
	lw	s6,12(sp)
	lw	s7,8(sp)
	lw	s8,4(sp)
	lw	s9,0(sp)
	lw	ra,40(sp)
	addi	sp,sp,44
	jr	ra

	.text
	.global	QS_Print
QS_Print:
	addi	sp,sp,-20
	sw	ra,16(sp)
	sw	s0,12(sp)
	sw	s1,8(sp)
	sw	s2,4(sp)
	sw	s3,0(sp)
	mv	s0,a0
	li	s1,0
L18:	lw	s2,4(s0)
	slt	s2,s1,s2
	beqz	s2,L19
	lw	s3,0(s0)
	li	s10,4
	mul	s2,s1,s10
	add	s2,s3,s2
	lw	s2,0(s2)
	mv	s10,s2
	jal	_print
	addi	s1,s1,1
	j	L18
L19:	addi	zero,zero,0
	li	s10,0
	lw	s0,12(sp)
	lw	s1,8(sp)
	lw	s2,4(sp)
	lw	s3,0(sp)
	lw	ra,16(sp)
	addi	sp,sp,20
	jr	ra

	.text
	.global	QS_Init
QS_Init:
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

