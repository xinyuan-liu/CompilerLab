	.text
	.global	main
main:
	addi	sp,sp,-4
	sw	ra,0(sp)
	li	s10,24
	jal	_hallocate
	mv	s0,s10
	la	s1,LS_Start
	sw	s1,8(s0)
	la	s1,LS_Print
	sw	s1,12(s0)
	la	s1,LS_Search
	sw	s1,16(s0)
	la	s1,LS_Init
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
	.global	LS_Start
LS_Start:
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
	lw	s1,12(s2)
	mv	a0,s2
	jalr	s1
	mv	s1,s10
	li	s10,9999
	jal	_print
	mv	s3,s0
	lw	s2,16(s3)
	li	s1,8
	mv	a0,s3
	mv	a1,s1
	jalr	s2
	mv	s1,s10
	mv	s10,s1
	jal	_print
	mv	s3,s0
	lw	s2,16(s3)
	li	s1,12
	mv	a0,s3
	mv	a1,s1
	jalr	s2
	mv	s1,s10
	mv	s10,s1
	jal	_print
	mv	s3,s0
	lw	s2,16(s3)
	li	s1,17
	mv	a0,s3
	mv	a1,s1
	jalr	s2
	mv	s1,s10
	mv	s10,s1
	jal	_print
	mv	s2,s0
	lw	s1,16(s2)
	li	s0,50
	mv	a0,s2
	mv	a1,s0
	jalr	s1
	mv	s0,s10
	mv	s10,s0
	jal	_print
	li	s10,55
	lw	s0,12(sp)
	lw	s1,8(sp)
	lw	s2,4(sp)
	lw	s3,0(sp)
	lw	ra,16(sp)
	addi	sp,sp,20
	jr	ra

	.text
	.global	LS_Print
LS_Print:
	addi	sp,sp,-20
	sw	ra,16(sp)
	sw	s0,12(sp)
	sw	s1,8(sp)
	sw	s2,4(sp)
	sw	s3,0(sp)
	mv	s0,a0
	li	s1,1
L4:	lw	s2,4(s0)
	slt	s2,s1,s2
	beqz	s2,L5
	lw	s3,0(s0)
	li	s10,4
	mul	s2,s1,s10
	add	s2,s3,s2
	lw	s2,0(s2)
	mv	s10,s2
	jal	_print
	addi	s1,s1,1
	j	L4
L5:	addi	zero,zero,0
	li	s10,0
	lw	s0,12(sp)
	lw	s1,8(sp)
	lw	s2,4(sp)
	lw	s3,0(sp)
	lw	ra,16(sp)
	addi	sp,sp,20
	jr	ra

	.text
	.global	LS_Search
LS_Search:
	addi	sp,sp,-32
	sw	ra,28(sp)
	sw	s0,24(sp)
	sw	s1,20(sp)
	sw	s2,16(sp)
	sw	s3,12(sp)
	sw	s4,8(sp)
	sw	s5,4(sp)
	sw	s6,0(sp)
	mv	s1,a0
	mv	s0,a1
	li	s3,1
	li	s2,0
	li	s2,0
L6:	lw	s4,4(s1)
	slt	s4,s3,s4
	beqz	s4,L7
	lw	s5,0(s1)
	li	s10,4
	mul	s4,s3,s10
	add	s4,s5,s4
	lw	s4,0(s4)
	mv	s6,s4
	addi	s5,s0,1
	slt	s4,s6,s0
	beqz	s4,L8
	li	s4,0
	j	L9
L8:	slt	s4,s6,s5
	slti	s4,s4,1
	beqz	s4,L10
	li	s4,0
	j	L11
L10:	li	s2,1
	li	s2,1
	lw	s3,4(s1)
	mv	s3,s3
L11:	addi	zero,zero,0
L9:	addi	zero,zero,0
	addi	s3,s3,1
	j	L6
L7:	addi	zero,zero,0
	mv	s10,s2
	lw	s0,24(sp)
	lw	s1,20(sp)
	lw	s2,16(sp)
	lw	s3,12(sp)
	lw	s4,8(sp)
	lw	s5,4(sp)
	lw	s6,0(sp)
	lw	ra,28(sp)
	addi	sp,sp,32
	jr	ra

	.text
	.global	LS_Init
LS_Init:
	addi	sp,sp,-32
	sw	ra,28(sp)
	sw	s0,24(sp)
	sw	s1,20(sp)
	sw	s2,16(sp)
	sw	s3,12(sp)
	sw	s4,8(sp)
	sw	s5,4(sp)
	sw	s6,0(sp)
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
	li	s1,1
	lw	s2,4(s0)
	addi	s2,s2,1
L12:	lw	s3,4(s0)
	slt	s3,s1,s3
	beqz	s3,L13
	li	s3,2
	mul	s4,s3,s1
	addi	s3,s2,-3
	lw	s6,0(s0)
	li	s10,4
	mul	s5,s1,s10
	add	s5,s6,s5
	add	s3,s4,s3
	sw	s3,0(s5)
	addi	s1,s1,1
	addi	s2,s2,-1
	j	L12
L13:	addi	zero,zero,0
	li	s10,0
	lw	s0,24(sp)
	lw	s1,20(sp)
	lw	s2,16(sp)
	lw	s3,12(sp)
	lw	s4,8(sp)
	lw	s5,4(sp)
	lw	s6,0(sp)
	lw	ra,28(sp)
	addi	sp,sp,32
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

