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
        add     sp,sp,-8
        sd      t0,0(sp)
	sw	s10,-68(s0)
	lw	a5,-68(s0)
	bnez	a5,.LS4
	lui	a5,%hi(.LC0)
	add	a0,a5,%lo(.LC0)
	call	puts
	j	.LS11
.LS4:
	lw	a5,-68(s0)
	bgez	a5,.LS6
	li	a0,45
	call	putchar
	lw	a5,-68(s0)
	subw	a5,zero,a5
	sw	a5,-68(s0)
.LS6:
	sw	zero,-20(s0)
	j	.LS7
.LS8:
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
.LS7:
	lw	a5,-68(s0)
	bnez	a5,.LS8
	sw	zero,-24(s0)
	j	.LS9
.LS10:
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
.LS9:
	lw	a4,-24(s0)
	lw	a5,-20(s0)
	blt	a4,a5,.LS10
	lw	a5,-20(s0)
	add	a4,s0,-16
	add	a5,a4,a5
	sb	zero,-40(a5)
	add	a5,s0,-56
	mv	a0,a5
	call	puts
.LS11:
	nop
        ld      t0,0(sp)
        add     sp,sp,8
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

	.section	.rodata
	.align	3
.LC1:
	.string	"ERROR"
	.text
	.align	2
	.globl	_error
	.type	_error, @function
_error:
	add	sp,sp,-16
	sd	ra,8(sp)
	sd	s0,0(sp)
	add	s0,sp,16
	lui	a5,%hi(.LC1)
	add	a0,a5,%lo(.LC1)
	call	puts
	li	a0,1
	call	exit
	.size	_error, .-_error
