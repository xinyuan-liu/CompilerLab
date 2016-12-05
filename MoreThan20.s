	.text
	.global	main
main:
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
	li	s10,4
	jal	_hallocate
	mv	s1,s10
	la	s0,A_run
	sw	s0,0(s1)
	mv	a0,s1
	lw	t5,0(a0)
	li	a1,1
	li	a2,2
	li	a3,3
	li	a4,4
	li	a5,5
	li	a6,6
	li	t6,7
	li	t4,8
	li	t3,9
	li	t2,0
	li	t1,1
	li	t0,2
	li	s9,3
	li	s8,4
	li	s7,5
	li	s6,6
	li	s5,7
	li	s4,8
	li	s3,9
	li	s2,0
	li	s1,21
	li	s0,22
	mv	a0,a0
	mv	a1,a1
	mv	a2,a2
	mv	a3,a3
	mv	a4,a4
	mv	a5,a5
	mv	a6,a6
	mv	a7,t6
	sw	t4,-8(sp)
	sw	t3,-12(sp)
	sw	t2,-16(sp)
	sw	t1,-20(sp)
	sw	t0,-24(sp)
	sw	s9,-28(sp)
	sw	s8,-32(sp)
	sw	s7,-36(sp)
	sw	s6,-40(sp)
	sw	s5,-44(sp)
	sw	s4,-48(sp)
	sw	s3,-52(sp)
	sw	s2,-56(sp)
	sw	s1,-60(sp)
	sw	s0,-64(sp)
	jalr	t5
	mv	s0,s10
	mv	s10,s0
	jal	_print
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
	.global	A_run
A_run:
	addi	sp,sp,-68
	sw	ra,64(sp)
	sw	s0,0(sp)
	mv	s0,a0
	mv	s0,a1
	mv	s0,a2
	mv	s0,a3
	mv	s0,a4
	mv	s0,a5
	mv	s0,a6
	mv	s0,a7
	lw	s0,60(sp)
	lw	s0,56(sp)
	lw	s0,52(sp)
	lw	s0,48(sp)
	lw	s0,44(sp)
	lw	s0,40(sp)
	lw	s0,36(sp)
	lw	s0,32(sp)
	lw	s0,28(sp)
	lw	s0,24(sp)
	lw	s0,20(sp)
	lw	s0,16(sp)
	lw	s0,12(sp)
	lw	s0,8(sp)
	lw	s0,4(sp)
	mv	s10,s0
	jal	_print
	li	s10,0
	lw	s0,0(sp)
	lw	ra,64(sp)
	addi	sp,sp,68
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

