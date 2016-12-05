	.text
	.global	main
main:
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
	li	s10,8
	jal	_hallocate
	mv	s0,s10
	la	s1,MT4_Start
	sw	s1,0(s0)
	la	s1,MT4_Change
	sw	s1,4(s0)
	mv	s7,s0
	lw	s6,0(s7)
	li	s5,1
	li	s4,2
	li	s3,3
	li	s2,4
	li	s1,5
	li	s0,6
	mv	a0,s7
	mv	a1,s5
	mv	a2,s4
	mv	a3,s3
	mv	a4,s2
	mv	a5,s1
	mv	a6,s0
	jalr	s6
	mv	s0,s10
	mv	s10,s0
	jal	_print
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
	.global	MT4_Start
MT4_Start:
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
	mv	s6,a0
	mv	s5,a1
	mv	s4,a2
	mv	s3,a3
	mv	s2,a4
	mv	s1,a5
	mv	s0,a6
	mv	s10,s5
	jal	_print
	mv	s10,s4
	jal	_print
	mv	s10,s3
	jal	_print
	mv	s10,s2
	jal	_print
	mv	s10,s1
	jal	_print
	mv	s10,s0
	jal	_print
	mv	s7,s6
	lw	s6,4(s7)
	mv	a0,s7
	mv	a1,s0
	mv	a2,s1
	mv	a3,s2
	mv	a4,s3
	mv	a5,s4
	mv	a6,s5
	jalr	s6
	mv	s0,s10
	mv	s10,s0
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
	.global	MT4_Change
MT4_Change:
	addi	sp,sp,-28
	sw	ra,24(sp)
	sw	s0,20(sp)
	sw	s1,16(sp)
	sw	s2,12(sp)
	sw	s3,8(sp)
	sw	s4,4(sp)
	sw	s5,0(sp)
	mv	s0,a0
	mv	s5,a1
	mv	s4,a2
	mv	s3,a3
	mv	s2,a4
	mv	s1,a5
	mv	s0,a6
	mv	s10,s5
	jal	_print
	mv	s10,s4
	jal	_print
	mv	s10,s3
	jal	_print
	mv	s10,s2
	jal	_print
	mv	s10,s1
	jal	_print
	mv	s10,s0
	jal	_print
	li	s10,0
	lw	s0,20(sp)
	lw	s1,16(sp)
	lw	s2,12(sp)
	lw	s3,8(sp)
	lw	s4,4(sp)
	lw	s5,0(sp)
	lw	ra,24(sp)
	addi	sp,sp,28
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

