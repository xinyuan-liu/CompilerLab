	.text
	.global	main
main:
	addi	sp,sp,-4
	sw	ra,0(sp)
	li	s10,4
	jal	_hallocate
	mv	s1,s10
	la	s0,BT_Start
	sw	s0,0(s1)
	mv	s1,s1
	lw	s0,0(s1)
	mv	a0,s1
	jalr	s0
	mv	s0,s10
	mv	s10,s0
	jal	_print
	lw	ra,0(sp)
	addi	sp,sp,4
	jr	ra

	.text
	.global	BT_Start
BT_Start:
	addi	sp,sp,-20
	sw	ra,16(sp)
	sw	s0,12(sp)
	sw	s1,8(sp)
	sw	s2,4(sp)
	mv	s0,a0
	li	s10,104
	jal	_hallocate
	mv	s0,s10
	la	s1,Tree_Init
	sw	s1,24(s0)
	la	s1,Tree_SetRight
	sw	s1,28(s0)
	la	s1,Tree_SetLeft
	sw	s1,32(s0)
	la	s1,Tree_GetRight
	sw	s1,36(s0)
	la	s1,Tree_GetLeft
	sw	s1,40(s0)
	la	s1,Tree_GetKey
	sw	s1,44(s0)
	la	s1,Tree_SetKey
	sw	s1,48(s0)
	la	s1,Tree_GetHas_Right
	sw	s1,52(s0)
	la	s1,Tree_GetHas_Left
	sw	s1,56(s0)
	la	s1,Tree_SetHas_Left
	sw	s1,60(s0)
	la	s1,Tree_SetHas_Right
	sw	s1,64(s0)
	la	s1,Tree_Compare
	sw	s1,68(s0)
	la	s1,Tree_Insert
	sw	s1,72(s0)
	la	s1,Tree_Delete
	sw	s1,76(s0)
	la	s1,Tree_Remove
	sw	s1,80(s0)
	la	s1,Tree_RemoveRight
	sw	s1,84(s0)
	la	s1,Tree_RemoveLeft
	sw	s1,88(s0)
	la	s1,Tree_Search
	sw	s1,92(s0)
	la	s1,Tree_Print
	sw	s1,96(s0)
	la	s1,Tree_RecPrint
	sw	s1,100(s0)
	mv	s10,s0
	sw	s10,0(sp)
	lw	s10,0(sp)
	mv	s2,s10
	lw	s1,24(s2)
	li	s0,16
	mv	a0,s2
	mv	a1,s0
	jalr	s1
	mv	s0,s10
	lw	s10,0(sp)
	mv	s1,s10
	lw	s0,96(s1)
	mv	a0,s1
	jalr	s0
	mv	s0,s10
	li	s10,100000000
	jal	_print
	lw	s10,0(sp)
	mv	s2,s10
	lw	s1,72(s2)
	li	s0,8
	mv	a0,s2
	mv	a1,s0
	jalr	s1
	mv	s0,s10
	lw	s10,0(sp)
	mv	s1,s10
	lw	s0,96(s1)
	mv	a0,s1
	jalr	s0
	mv	s0,s10
	lw	s10,0(sp)
	mv	s2,s10
	lw	s1,72(s2)
	li	s0,24
	mv	a0,s2
	mv	a1,s0
	jalr	s1
	mv	s0,s10
	lw	s10,0(sp)
	mv	s2,s10
	lw	s1,72(s2)
	li	s0,4
	mv	a0,s2
	mv	a1,s0
	jalr	s1
	mv	s0,s10
	lw	s10,0(sp)
	mv	s2,s10
	lw	s1,72(s2)
	li	s0,12
	mv	a0,s2
	mv	a1,s0
	jalr	s1
	mv	s0,s10
	lw	s10,0(sp)
	mv	s2,s10
	lw	s1,72(s2)
	li	s0,20
	mv	a0,s2
	mv	a1,s0
	jalr	s1
	mv	s0,s10
	lw	s10,0(sp)
	mv	s2,s10
	lw	s1,72(s2)
	li	s0,28
	mv	a0,s2
	mv	a1,s0
	jalr	s1
	mv	s0,s10
	lw	s10,0(sp)
	mv	s2,s10
	lw	s1,72(s2)
	li	s0,14
	mv	a0,s2
	mv	a1,s0
	jalr	s1
	mv	s0,s10
	lw	s10,0(sp)
	mv	s1,s10
	lw	s0,96(s1)
	mv	a0,s1
	jalr	s0
	mv	s0,s10
	lw	s10,0(sp)
	mv	s2,s10
	lw	s1,92(s2)
	li	s0,24
	mv	a0,s2
	mv	a1,s0
	jalr	s1
	mv	s0,s10
	mv	s10,s0
	jal	_print
	lw	s10,0(sp)
	mv	s2,s10
	lw	s1,92(s2)
	li	s0,12
	mv	a0,s2
	mv	a1,s0
	jalr	s1
	mv	s0,s10
	mv	s10,s0
	jal	_print
	lw	s10,0(sp)
	mv	s2,s10
	lw	s1,92(s2)
	li	s0,16
	mv	a0,s2
	mv	a1,s0
	jalr	s1
	mv	s0,s10
	mv	s10,s0
	jal	_print
	lw	s10,0(sp)
	mv	s2,s10
	lw	s1,92(s2)
	li	s0,50
	mv	a0,s2
	mv	a1,s0
	jalr	s1
	mv	s0,s10
	mv	s10,s0
	jal	_print
	lw	s10,0(sp)
	mv	s2,s10
	lw	s1,92(s2)
	li	s0,12
	mv	a0,s2
	mv	a1,s0
	jalr	s1
	mv	s0,s10
	mv	s10,s0
	jal	_print
	lw	s10,0(sp)
	mv	s2,s10
	lw	s1,76(s2)
	li	s0,12
	mv	a0,s2
	mv	a1,s0
	jalr	s1
	mv	s0,s10
	lw	s10,0(sp)
	mv	s1,s10
	lw	s0,96(s1)
	mv	a0,s1
	jalr	s0
	mv	s0,s10
	lw	s10,0(sp)
	mv	s2,s10
	lw	s1,92(s2)
	li	s0,12
	mv	a0,s2
	mv	a1,s0
	jalr	s1
	mv	s0,s10
	mv	s10,s0
	jal	_print
	li	s10,0
	lw	s0,12(sp)
	lw	s1,8(sp)
	lw	s2,4(sp)
	lw	ra,16(sp)
	addi	sp,sp,20
	jr	ra

	.text
	.global	Tree_Init
Tree_Init:
	addi	sp,sp,-12
	sw	ra,8(sp)
	sw	s0,4(sp)
	sw	s1,0(sp)
	mv	s0,a0
	mv	s1,a1
	sw	s1,8(s0)
	li	s1,0
	sw	s1,12(s0)
	li	s1,0
	sw	s1,16(s0)
	li	s10,1
	lw	s0,4(sp)
	lw	s1,0(sp)
	lw	ra,8(sp)
	addi	sp,sp,12
	jr	ra

	.text
	.global	Tree_SetRight
Tree_SetRight:
	addi	sp,sp,-12
	sw	ra,8(sp)
	sw	s0,4(sp)
	sw	s1,0(sp)
	mv	s1,a0
	mv	s0,a1
	sw	s0,4(s1)
	li	s10,1
	lw	s0,4(sp)
	lw	s1,0(sp)
	lw	ra,8(sp)
	addi	sp,sp,12
	jr	ra

	.text
	.global	Tree_SetLeft
Tree_SetLeft:
	addi	sp,sp,-12
	sw	ra,8(sp)
	sw	s0,4(sp)
	sw	s1,0(sp)
	mv	s1,a0
	mv	s0,a1
	sw	s0,0(s1)
	li	s10,1
	lw	s0,4(sp)
	lw	s1,0(sp)
	lw	ra,8(sp)
	addi	sp,sp,12
	jr	ra

	.text
	.global	Tree_GetRight
Tree_GetRight:
	addi	sp,sp,-8
	sw	ra,4(sp)
	sw	s0,0(sp)
	mv	s0,a0
	lw	s0,4(s0)
	mv	s10,s0
	lw	s0,0(sp)
	lw	ra,4(sp)
	addi	sp,sp,8
	jr	ra

	.text
	.global	Tree_GetLeft
Tree_GetLeft:
	addi	sp,sp,-8
	sw	ra,4(sp)
	sw	s0,0(sp)
	mv	s0,a0
	lw	s0,0(s0)
	mv	s10,s0
	lw	s0,0(sp)
	lw	ra,4(sp)
	addi	sp,sp,8
	jr	ra

	.text
	.global	Tree_GetKey
Tree_GetKey:
	addi	sp,sp,-8
	sw	ra,4(sp)
	sw	s0,0(sp)
	mv	s0,a0
	lw	s0,8(s0)
	mv	s10,s0
	lw	s0,0(sp)
	lw	ra,4(sp)
	addi	sp,sp,8
	jr	ra

	.text
	.global	Tree_SetKey
Tree_SetKey:
	addi	sp,sp,-12
	sw	ra,8(sp)
	sw	s0,4(sp)
	sw	s1,0(sp)
	mv	s1,a0
	mv	s0,a1
	sw	s0,8(s1)
	li	s10,1
	lw	s0,4(sp)
	lw	s1,0(sp)
	lw	ra,8(sp)
	addi	sp,sp,12
	jr	ra

	.text
	.global	Tree_GetHas_Right
Tree_GetHas_Right:
	addi	sp,sp,-8
	sw	ra,4(sp)
	sw	s0,0(sp)
	mv	s0,a0
	lw	s0,16(s0)
	mv	s10,s0
	lw	s0,0(sp)
	lw	ra,4(sp)
	addi	sp,sp,8
	jr	ra

	.text
	.global	Tree_GetHas_Left
Tree_GetHas_Left:
	addi	sp,sp,-8
	sw	ra,4(sp)
	sw	s0,0(sp)
	mv	s0,a0
	lw	s0,12(s0)
	mv	s10,s0
	lw	s0,0(sp)
	lw	ra,4(sp)
	addi	sp,sp,8
	jr	ra

	.text
	.global	Tree_SetHas_Left
Tree_SetHas_Left:
	addi	sp,sp,-12
	sw	ra,8(sp)
	sw	s0,4(sp)
	sw	s1,0(sp)
	mv	s1,a0
	mv	s0,a1
	sw	s0,12(s1)
	li	s10,1
	lw	s0,4(sp)
	lw	s1,0(sp)
	lw	ra,8(sp)
	addi	sp,sp,12
	jr	ra

	.text
	.global	Tree_SetHas_Right
Tree_SetHas_Right:
	addi	sp,sp,-12
	sw	ra,8(sp)
	sw	s0,4(sp)
	sw	s1,0(sp)
	mv	s1,a0
	mv	s0,a1
	sw	s0,16(s1)
	li	s10,1
	lw	s0,4(sp)
	lw	s1,0(sp)
	lw	ra,8(sp)
	addi	sp,sp,12
	jr	ra

	.text
	.global	Tree_Compare
Tree_Compare:
	addi	sp,sp,-16
	sw	ra,12(sp)
	sw	s0,8(sp)
	sw	s1,4(sp)
	sw	s2,0(sp)
	mv	s0,a0
	mv	s0,a1
	mv	s1,a2
	li	s2,0
	addi	s2,s1,1
	slt	s1,s0,s1
	beqz	s1,L21
	li	s0,0
	j	L22
L21:	slt	s0,s0,s2
	slti	s0,s0,1
	beqz	s0,L23
	li	s0,0
	j	L24
L23:	li	s0,1
L24:	addi	zero,zero,0
L22:	addi	zero,zero,0
	mv	s10,s0
	lw	s0,8(sp)
	lw	s1,4(sp)
	lw	s2,0(sp)
	lw	ra,12(sp)
	addi	sp,sp,16
	jr	ra

	.text
	.global	Tree_Insert
Tree_Insert:
	addi	sp,sp,-32
	sw	ra,28(sp)
	sw	s0,24(sp)
	sw	s1,20(sp)
	sw	s2,16(sp)
	sw	s3,12(sp)
	sw	s4,8(sp)
	sw	s5,4(sp)
	sw	s6,0(sp)
	mv	s2,a0
	mv	s0,a1
	li	s10,104
	jal	_hallocate
	mv	s1,s10
	la	s3,Tree_Init
	sw	s3,24(s1)
	la	s3,Tree_SetRight
	sw	s3,28(s1)
	la	s3,Tree_SetLeft
	sw	s3,32(s1)
	la	s3,Tree_GetRight
	sw	s3,36(s1)
	la	s3,Tree_GetLeft
	sw	s3,40(s1)
	la	s3,Tree_GetKey
	sw	s3,44(s1)
	la	s3,Tree_SetKey
	sw	s3,48(s1)
	la	s3,Tree_GetHas_Right
	sw	s3,52(s1)
	la	s3,Tree_GetHas_Left
	sw	s3,56(s1)
	la	s3,Tree_SetHas_Left
	sw	s3,60(s1)
	la	s3,Tree_SetHas_Right
	sw	s3,64(s1)
	la	s3,Tree_Compare
	sw	s3,68(s1)
	la	s3,Tree_Insert
	sw	s3,72(s1)
	la	s3,Tree_Delete
	sw	s3,76(s1)
	la	s3,Tree_Remove
	sw	s3,80(s1)
	la	s3,Tree_RemoveRight
	sw	s3,84(s1)
	la	s3,Tree_RemoveLeft
	sw	s3,88(s1)
	la	s3,Tree_Search
	sw	s3,92(s1)
	la	s3,Tree_Print
	sw	s3,96(s1)
	la	s3,Tree_RecPrint
	sw	s3,100(s1)
	mv	s1,s1
	mv	s4,s1
	lw	s3,24(s4)
	mv	a0,s4
	mv	a1,s0
	jalr	s3
	mv	s3,s10
	mv	s3,s2
	li	s2,1
L25:	beqz	s2,L26
	mv	s5,s3
	lw	s4,44(s5)
	mv	a0,s5
	jalr	s4
	mv	s4,s10
	slt	s4,s0,s4
	beqz	s4,L27
	mv	s5,s3
	lw	s4,56(s5)
	mv	a0,s5
	jalr	s4
	mv	s4,s10
	beqz	s4,L28
	mv	s4,s3
	lw	s3,40(s4)
	mv	a0,s4
	jalr	s3
	mv	s3,s10
	j	L29
L28:	li	s2,0
	mv	s6,s3
	lw	s5,60(s6)
	li	s4,1
	mv	a0,s6
	mv	a1,s4
	jalr	s5
	mv	s4,s10
	mv	s5,s3
	lw	s4,32(s5)
	mv	a0,s5
	mv	a1,s1
	jalr	s4
	mv	s4,s10
L29:	addi	zero,zero,0
	j	L30
L27:	mv	s5,s3
	lw	s4,52(s5)
	mv	a0,s5
	jalr	s4
	mv	s4,s10
	beqz	s4,L31
	mv	s4,s3
	lw	s3,36(s4)
	mv	a0,s4
	jalr	s3
	mv	s3,s10
	j	L32
L31:	li	s2,0
	mv	s6,s3
	lw	s5,64(s6)
	li	s4,1
	mv	a0,s6
	mv	a1,s4
	jalr	s5
	mv	s4,s10
	mv	s5,s3
	lw	s4,28(s5)
	mv	a0,s5
	mv	a1,s1
	jalr	s4
	mv	s4,s10
L32:	addi	zero,zero,0
L30:	addi	zero,zero,0
	j	L25
L26:	addi	zero,zero,0
	li	s10,1
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
	.global	Tree_Delete
Tree_Delete:
	addi	sp,sp,-40
	sw	ra,36(sp)
	sw	s0,32(sp)
	sw	s1,28(sp)
	sw	s2,24(sp)
	sw	s3,20(sp)
	sw	s4,16(sp)
	sw	s5,12(sp)
	sw	s6,8(sp)
	sw	s7,4(sp)
	sw	s8,0(sp)
	mv	s1,a0
	mv	s0,a1
	mv	s3,s1
	mv	s2,s1
	li	s5,1
	li	s4,0
	li	s6,1
L33:	beqz	s5,L34
	mv	s8,s3
	lw	s7,44(s8)
	mv	a0,s8
	jalr	s7
	mv	s8,s10
	slt	s7,s0,s8
	beqz	s7,L35
	mv	s7,s3
	lw	s6,56(s7)
	mv	a0,s7
	jalr	s6
	mv	s6,s10
	beqz	s6,L36
	mv	s2,s3
	mv	s6,s3
	lw	s3,40(s6)
	mv	a0,s6
	jalr	s3
	mv	s3,s10
	j	L37
L36:	li	s5,0
L37:	addi	zero,zero,0
	j	L38
L35:	slt	s7,s8,s0
	beqz	s7,L39
	mv	s7,s3
	lw	s6,52(s7)
	mv	a0,s7
	jalr	s6
	mv	s6,s10
	beqz	s6,L40
	mv	s2,s3
	mv	s6,s3
	lw	s3,36(s6)
	mv	a0,s6
	jalr	s3
	mv	s3,s10
	j	L41
L40:	li	s5,0
L41:	addi	zero,zero,0
	j	L42
L39:	beqz	s6,L43
	li	s4,0
	mv	s6,s3
	lw	s5,52(s6)
	mv	a0,s6
	jalr	s5
	mv	s5,s10
	slti	s5,s5,1
	beqz	s5,L44
	mv	s6,s3
	lw	s5,56(s6)
	mv	a0,s6
	jalr	s5
	mv	s5,s10
	slti	s5,s5,1
	beqz	s5,L44
	li	s4,1
L44:	addi	zero,zero,0
	beqz	s4,L45
	li	s4,1
	j	L46
L45:	mv	s5,s1
	lw	s4,80(s5)
	mv	a0,s5
	mv	a1,s2
	mv	a2,s3
	jalr	s4
	mv	s4,s10
L46:	addi	zero,zero,0
	j	L47
L43:	mv	s5,s1
	lw	s4,80(s5)
	mv	a0,s5
	mv	a1,s2
	mv	a2,s3
	jalr	s4
	mv	s4,s10
L47:	addi	zero,zero,0
	li	s4,1
	li	s5,0
L42:	addi	zero,zero,0
L38:	addi	zero,zero,0
	li	s6,0
	j	L33
L34:	addi	zero,zero,0
	mv	s10,s4
	lw	s0,32(sp)
	lw	s1,28(sp)
	lw	s2,24(sp)
	lw	s3,20(sp)
	lw	s4,16(sp)
	lw	s5,12(sp)
	lw	s6,8(sp)
	lw	s7,4(sp)
	lw	s8,0(sp)
	lw	ra,36(sp)
	addi	sp,sp,40
	jr	ra

	.text
	.global	Tree_Remove
Tree_Remove:
	addi	sp,sp,-28
	sw	ra,24(sp)
	sw	s0,20(sp)
	sw	s1,16(sp)
	sw	s2,12(sp)
	sw	s3,8(sp)
	sw	s4,4(sp)
	sw	s5,0(sp)
	mv	s1,a0
	mv	s0,a1
	mv	s2,a2
	mv	s4,s2
	lw	s3,56(s4)
	mv	a0,s4
	jalr	s3
	mv	s3,s10
	beqz	s3,L48
	mv	s3,s1
	lw	s1,88(s3)
	mv	a0,s3
	mv	a1,s0
	mv	a2,s2
	jalr	s1
	mv	s0,s10
	j	L49
L48:	mv	s4,s2
	lw	s3,52(s4)
	mv	a0,s4
	jalr	s3
	mv	s3,s10
	beqz	s3,L50
	mv	s3,s1
	lw	s1,84(s3)
	mv	a0,s3
	mv	a1,s0
	mv	a2,s2
	jalr	s1
	mv	s0,s10
	j	L51
L50:	mv	s3,s2
	lw	s2,44(s3)
	mv	a0,s3
	jalr	s2
	mv	s2,s10
	mv	s4,s0
	lw	s3,40(s4)
	mv	a0,s4
	jalr	s3
	mv	s4,s10
	lw	s3,44(s4)
	mv	a0,s4
	jalr	s3
	mv	s5,s10
	mv	s4,s1
	lw	s3,68(s4)
	mv	a0,s4
	mv	a1,s2
	mv	a2,s5
	jalr	s3
	mv	s2,s10
	beqz	s2,L52
	mv	s3,s0
	lw	s2,32(s3)
	lw	s1,20(s1)
	mv	a0,s3
	mv	a1,s1
	jalr	s2
	mv	s1,s10
	mv	s2,s0
	lw	s1,60(s2)
	li	s0,0
	mv	a0,s2
	mv	a1,s0
	jalr	s1
	mv	s0,s10
	j	L53
L52:	mv	s3,s0
	lw	s2,28(s3)
	lw	s1,20(s1)
	mv	a0,s3
	mv	a1,s1
	jalr	s2
	mv	s1,s10
	mv	s2,s0
	lw	s1,64(s2)
	li	s0,0
	mv	a0,s2
	mv	a1,s0
	jalr	s1
	mv	s0,s10
L53:	addi	zero,zero,0
L51:	addi	zero,zero,0
L49:	addi	zero,zero,0
	li	s10,1
	lw	s0,20(sp)
	lw	s1,16(sp)
	lw	s2,12(sp)
	lw	s3,8(sp)
	lw	s4,4(sp)
	lw	s5,0(sp)
	lw	ra,24(sp)
	addi	sp,sp,28
	jr	ra

	.text
	.global	Tree_RemoveRight
Tree_RemoveRight:
	addi	sp,sp,-28
	sw	ra,24(sp)
	sw	s0,20(sp)
	sw	s1,16(sp)
	sw	s2,12(sp)
	sw	s3,8(sp)
	sw	s4,4(sp)
	sw	s5,0(sp)
	mv	s0,a0
	mv	s2,a1
	mv	s1,a2
L54:	mv	s4,s1
	lw	s3,52(s4)
	mv	a0,s4
	jalr	s3
	mv	s3,s10
	beqz	s3,L55
	mv	s3,s1
	lw	s2,48(s3)
	mv	s5,s1
	lw	s4,36(s5)
	mv	a0,s5
	jalr	s4
	mv	s5,s10
	lw	s4,44(s5)
	mv	a0,s5
	jalr	s4
	mv	s4,s10
	mv	a0,s3
	mv	a1,s4
	jalr	s2
	mv	s2,s10
	mv	s2,s1
	mv	s3,s1
	lw	s1,36(s3)
	mv	a0,s3
	jalr	s1
	mv	s1,s10
	j	L54
L55:	addi	zero,zero,0
	mv	s3,s2
	lw	s1,28(s3)
	lw	s0,20(s0)
	mv	a0,s3
	mv	a1,s0
	jalr	s1
	mv	s0,s10
	mv	s2,s2
	lw	s1,64(s2)
	li	s0,0
	mv	a0,s2
	mv	a1,s0
	jalr	s1
	mv	s0,s10
	li	s10,1
	lw	s0,20(sp)
	lw	s1,16(sp)
	lw	s2,12(sp)
	lw	s3,8(sp)
	lw	s4,4(sp)
	lw	s5,0(sp)
	lw	ra,24(sp)
	addi	sp,sp,28
	jr	ra

	.text
	.global	Tree_RemoveLeft
Tree_RemoveLeft:
	addi	sp,sp,-28
	sw	ra,24(sp)
	sw	s0,20(sp)
	sw	s1,16(sp)
	sw	s2,12(sp)
	sw	s3,8(sp)
	sw	s4,4(sp)
	sw	s5,0(sp)
	mv	s0,a0
	mv	s2,a1
	mv	s1,a2
L56:	mv	s4,s1
	lw	s3,56(s4)
	mv	a0,s4
	jalr	s3
	mv	s3,s10
	beqz	s3,L57
	mv	s3,s1
	lw	s2,48(s3)
	mv	s5,s1
	lw	s4,40(s5)
	mv	a0,s5
	jalr	s4
	mv	s5,s10
	lw	s4,44(s5)
	mv	a0,s5
	jalr	s4
	mv	s4,s10
	mv	a0,s3
	mv	a1,s4
	jalr	s2
	mv	s2,s10
	mv	s2,s1
	mv	s3,s1
	lw	s1,40(s3)
	mv	a0,s3
	jalr	s1
	mv	s1,s10
	j	L56
L57:	addi	zero,zero,0
	mv	s3,s2
	lw	s1,32(s3)
	lw	s0,20(s0)
	mv	a0,s3
	mv	a1,s0
	jalr	s1
	mv	s0,s10
	mv	s2,s2
	lw	s1,60(s2)
	li	s0,0
	mv	a0,s2
	mv	a1,s0
	jalr	s1
	mv	s0,s10
	li	s10,1
	lw	s0,20(sp)
	lw	s1,16(sp)
	lw	s2,12(sp)
	lw	s3,8(sp)
	lw	s4,4(sp)
	lw	s5,0(sp)
	lw	ra,24(sp)
	addi	sp,sp,28
	jr	ra

	.text
	.global	Tree_Search
Tree_Search:
	addi	sp,sp,-28
	sw	ra,24(sp)
	sw	s0,20(sp)
	sw	s1,16(sp)
	sw	s2,12(sp)
	sw	s3,8(sp)
	sw	s4,4(sp)
	sw	s5,0(sp)
	mv	s1,a0
	mv	s0,a1
	mv	s3,s1
	li	s2,1
	li	s1,0
L58:	beqz	s2,L59
	mv	s5,s3
	lw	s4,44(s5)
	mv	a0,s5
	jalr	s4
	mv	s5,s10
	slt	s4,s0,s5
	beqz	s4,L60
	mv	s5,s3
	lw	s4,56(s5)
	mv	a0,s5
	jalr	s4
	mv	s4,s10
	beqz	s4,L61
	mv	s4,s3
	lw	s3,40(s4)
	mv	a0,s4
	jalr	s3
	mv	s3,s10
	j	L62
L61:	li	s2,0
L62:	addi	zero,zero,0
	j	L63
L60:	slt	s4,s5,s0
	beqz	s4,L64
	mv	s5,s3
	lw	s4,52(s5)
	mv	a0,s5
	jalr	s4
	mv	s4,s10
	beqz	s4,L65
	mv	s4,s3
	lw	s3,36(s4)
	mv	a0,s4
	jalr	s3
	mv	s3,s10
	j	L66
L65:	li	s2,0
L66:	addi	zero,zero,0
	j	L67
L64:	li	s1,1
	li	s2,0
L67:	addi	zero,zero,0
L63:	addi	zero,zero,0
	j	L58
L59:	addi	zero,zero,0
	mv	s10,s1
	lw	s0,20(sp)
	lw	s1,16(sp)
	lw	s2,12(sp)
	lw	s3,8(sp)
	lw	s4,4(sp)
	lw	s5,0(sp)
	lw	ra,24(sp)
	addi	sp,sp,28
	jr	ra

	.text
	.global	Tree_Print
Tree_Print:
	addi	sp,sp,-16
	sw	ra,12(sp)
	sw	s0,8(sp)
	sw	s1,4(sp)
	sw	s2,0(sp)
	mv	s1,a0
	mv	s0,s1
	mv	s2,s1
	lw	s1,100(s2)
	mv	a0,s2
	mv	a1,s0
	jalr	s1
	mv	s0,s10
	li	s10,1
	lw	s0,8(sp)
	lw	s1,4(sp)
	lw	s2,0(sp)
	lw	ra,12(sp)
	addi	sp,sp,16
	jr	ra

	.text
	.global	Tree_RecPrint
Tree_RecPrint:
	addi	sp,sp,-28
	sw	ra,24(sp)
	sw	s0,20(sp)
	sw	s1,16(sp)
	sw	s2,12(sp)
	sw	s3,8(sp)
	sw	s4,4(sp)
	sw	s5,0(sp)
	mv	s1,a0
	mv	s0,a1
	mv	s3,s0
	lw	s2,56(s3)
	mv	a0,s3
	jalr	s2
	mv	s2,s10
	beqz	s2,L68
	mv	s3,s1
	lw	s2,100(s3)
	mv	s5,s0
	lw	s4,40(s5)
	mv	a0,s5
	jalr	s4
	mv	s4,s10
	mv	a0,s3
	mv	a1,s4
	jalr	s2
	mv	s2,s10
	j	L69
L68:	li	s2,1
L69:	addi	zero,zero,0
	mv	s3,s0
	lw	s2,44(s3)
	mv	a0,s3
	jalr	s2
	mv	s2,s10
	mv	s10,s2
	jal	_print
	mv	s3,s0
	lw	s2,52(s3)
	mv	a0,s3
	jalr	s2
	mv	s2,s10
	beqz	s2,L70
	mv	s2,s1
	lw	s1,100(s2)
	mv	s3,s0
	lw	s0,36(s3)
	mv	a0,s3
	jalr	s0
	mv	s0,s10
	mv	a0,s2
	mv	a1,s0
	jalr	s1
	mv	s0,s10
	j	L71
L70:	li	s0,1
L71:	addi	zero,zero,0
	li	s10,1
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

