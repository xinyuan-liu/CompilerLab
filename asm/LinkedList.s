	.text
	.global	main
main:
	addi	sp,sp,-4
	sw	ra,0(sp)
	li	s10,4
	jal	_hallocate
	mv	s1,s10
	la	s0,LL_Start
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
	.global	Element_Init
Element_Init:
	addi	sp,sp,-20
	sw	ra,16(sp)
	sw	s0,12(sp)
	sw	s1,8(sp)
	sw	s2,4(sp)
	sw	s3,0(sp)
	mv	s3,a0
	mv	s2,a1
	mv	s1,a2
	mv	s0,a3
	sw	s2,0(s3)
	sw	s1,4(s3)
	sw	s0,8(s3)
	li	s10,1
	lw	s0,12(sp)
	lw	s1,8(sp)
	lw	s2,4(sp)
	lw	s3,0(sp)
	lw	ra,16(sp)
	addi	sp,sp,20
	jr	ra

	.text
	.global	Element_GetAge
Element_GetAge:
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
	.global	Element_GetSalary
Element_GetSalary:
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
	.global	Element_GetMarried
Element_GetMarried:
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
	.global	Element_Equal
Element_Equal:
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
	mv	s1,a1
	li	s0,1
	mv	s4,s1
	lw	s3,16(s4)
	mv	a0,s4
	jalr	s3
	mv	s6,s10
	mv	s5,s2
	lw	s4,32(s5)
	lw	s3,0(s2)
	mv	a0,s5
	mv	a1,s6
	mv	a2,s3
	jalr	s4
	mv	s3,s10
	slti	s3,s3,1
	beqz	s3,L5
	li	s0,0
	j	L6
L5:	mv	s4,s1
	lw	s3,20(s4)
	mv	a0,s4
	jalr	s3
	mv	s6,s10
	mv	s5,s2
	lw	s4,32(s5)
	lw	s3,4(s2)
	mv	a0,s5
	mv	a1,s6
	mv	a2,s3
	jalr	s4
	mv	s3,s10
	slti	s3,s3,1
	beqz	s3,L7
	li	s0,0
	j	L8
L7:	lw	s2,8(s2)
	beqz	s2,L9
	mv	s2,s1
	lw	s1,24(s2)
	mv	a0,s2
	jalr	s1
	mv	s1,s10
	slti	s1,s1,1
	beqz	s1,L10
	li	s0,0
	j	L11
L10:	li	s1,0
L11:	addi	zero,zero,0
	j	L12
L9:	mv	s2,s1
	lw	s1,24(s2)
	mv	a0,s2
	jalr	s1
	mv	s1,s10
	beqz	s1,L13
	li	s0,0
	j	L14
L13:	li	s1,0
L14:	addi	zero,zero,0
L12:	addi	zero,zero,0
L8:	addi	zero,zero,0
L6:	addi	zero,zero,0
	mv	s10,s0
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
	.global	Element_Compare
Element_Compare:
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
	beqz	s1,L16
	li	s0,0
	j	L17
L16:	slt	s0,s0,s2
	slti	s0,s0,1
	beqz	s0,L18
	li	s0,0
	j	L19
L18:	li	s0,1
L19:	addi	zero,zero,0
L17:	addi	zero,zero,0
	mv	s10,s0
	lw	s0,8(sp)
	lw	s1,4(sp)
	lw	s2,0(sp)
	lw	ra,12(sp)
	addi	sp,sp,16
	jr	ra

	.text
	.global	List_Init
List_Init:
	addi	sp,sp,-12
	sw	ra,8(sp)
	sw	s0,4(sp)
	sw	s1,0(sp)
	mv	s0,a0
	li	s1,1
	sw	s1,8(s0)
	li	s10,1
	lw	s0,4(sp)
	lw	s1,0(sp)
	lw	ra,8(sp)
	addi	sp,sp,12
	jr	ra

	.text
	.global	List_InitNew
List_InitNew:
	addi	sp,sp,-20
	sw	ra,16(sp)
	sw	s0,12(sp)
	sw	s1,8(sp)
	sw	s2,4(sp)
	sw	s3,0(sp)
	mv	s3,a0
	mv	s2,a1
	mv	s1,a2
	mv	s0,a3
	sw	s0,8(s3)
	sw	s2,0(s3)
	sw	s1,4(s3)
	li	s10,1
	lw	s0,12(sp)
	lw	s1,8(sp)
	lw	s2,4(sp)
	lw	s3,0(sp)
	lw	ra,16(sp)
	addi	sp,sp,20
	jr	ra

	.text
	.global	List_Insert
List_Insert:
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
	mv	s1,s1
	li	s10,52
	jal	_hallocate
	mv	s2,s10
	la	s3,List_Init
	sw	s3,12(s2)
	la	s3,List_InitNew
	sw	s3,16(s2)
	la	s3,List_Insert
	sw	s3,20(s2)
	la	s3,List_SetNext
	sw	s3,24(s2)
	la	s3,List_Delete
	sw	s3,28(s2)
	la	s3,List_Search
	sw	s3,32(s2)
	la	s3,List_GetEnd
	sw	s3,36(s2)
	la	s3,List_GetElem
	sw	s3,40(s2)
	la	s3,List_GetNext
	sw	s3,44(s2)
	la	s3,List_Print
	sw	s3,48(s2)
	mv	s2,s2
	mv	s5,s2
	lw	s4,16(s5)
	li	s3,0
	mv	a0,s5
	mv	a1,s0
	mv	a2,s1
	mv	a3,s3
	jalr	s4
	mv	s0,s10
	mv	s10,s2
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
	.global	List_SetNext
List_SetNext:
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
	.global	List_Delete
List_Delete:
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
	mv	s7,a0
	mv	s0,a1
	mv	s3,s7
	li	s1,0
	li	s2,0
	addi	s6,s2,-1
	mv	s4,s7
	mv	s2,s7
	lw	s5,8(s7)
	mv	s5,s5
	lw	s7,0(s7)
	mv	s7,s7
L31:	li	s8,0
	slti	s9,s5,1
	beqz	s9,L32
	slti	s9,s1,1
	beqz	s9,L32
	li	s8,1
L32:	addi	zero,zero,0
	beqz	s8,L33
	mv	s9,s0
	lw	s8,28(s9)
	mv	a0,s9
	mv	a1,s7
	jalr	s8
	mv	s8,s10
	beqz	s8,L34
	li	s1,1
	slti	s8,s6,0
	beqz	s8,L35
	mv	s8,s4
	lw	s3,44(s8)
	mv	a0,s8
	jalr	s3
	mv	s3,s10
	j	L36
L35:	li	s8,0
	addi	s8,s8,-555
	mv	s10,s8
	jal	_print
	mv	s9,s2
	lw	s8,24(s9)
	mv	t1,s4
	lw	t0,44(t1)
	mv	a0,t1
	jalr	t0
	mv	t0,s10
	mv	a0,s9
	mv	a1,t0
	jalr	s8
	mv	s8,s10
	li	s8,0
	addi	s8,s8,-555
	mv	s10,s8
	jal	_print
L36:	addi	zero,zero,0
	j	L37
L34:	li	s8,0
L37:	addi	zero,zero,0
	slti	s8,s1,1
	beqz	s8,L38
	mv	s2,s4
	mv	s5,s4
	lw	s4,44(s5)
	mv	a0,s5
	jalr	s4
	mv	s4,s10
	mv	s6,s4
	lw	s5,36(s6)
	mv	a0,s6
	jalr	s5
	mv	s5,s10
	mv	s7,s4
	lw	s6,40(s7)
	mv	a0,s7
	jalr	s6
	mv	s7,s10
	li	s6,1
	j	L39
L38:	li	s8,0
L39:	addi	zero,zero,0
	j	L31
L33:	addi	zero,zero,0
	mv	s10,s3
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
	.global	List_Search
List_Search:
	addi	sp,sp,-28
	sw	ra,24(sp)
	sw	s0,20(sp)
	sw	s1,16(sp)
	sw	s2,12(sp)
	sw	s3,8(sp)
	sw	s4,4(sp)
	sw	s5,0(sp)
	mv	s4,a0
	mv	s0,a1
	li	s1,0
	mv	s2,s4
	lw	s3,8(s4)
	mv	s3,s3
	lw	s4,0(s4)
	mv	s4,s4
L40:	slti	s3,s3,1
	beqz	s3,L41
	mv	s5,s0
	lw	s3,28(s5)
	mv	a0,s5
	mv	a1,s4
	jalr	s3
	mv	s3,s10
	beqz	s3,L42
	li	s1,1
	j	L43
L42:	li	s3,0
L43:	addi	zero,zero,0
	mv	s3,s2
	lw	s2,44(s3)
	mv	a0,s3
	jalr	s2
	mv	s2,s10
	mv	s4,s2
	lw	s3,36(s4)
	mv	a0,s4
	jalr	s3
	mv	s3,s10
	mv	s5,s2
	lw	s4,40(s5)
	mv	a0,s5
	jalr	s4
	mv	s4,s10
	j	L40
L41:	addi	zero,zero,0
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
	.global	List_GetEnd
List_GetEnd:
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
	.global	List_GetElem
List_GetElem:
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
	.global	List_GetNext
List_GetNext:
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
	.global	List_Print
List_Print:
	addi	sp,sp,-20
	sw	ra,16(sp)
	sw	s0,12(sp)
	sw	s1,8(sp)
	sw	s2,4(sp)
	sw	s3,0(sp)
	mv	s2,a0
	mv	s0,s2
	lw	s1,8(s2)
	mv	s1,s1
	lw	s2,0(s2)
	mv	s2,s2
L44:	slti	s1,s1,1
	beqz	s1,L45
	mv	s2,s2
	lw	s1,16(s2)
	mv	a0,s2
	jalr	s1
	mv	s1,s10
	mv	s10,s1
	jal	_print
	mv	s1,s0
	lw	s0,44(s1)
	mv	a0,s1
	jalr	s0
	mv	s0,s10
	mv	s2,s0
	lw	s1,36(s2)
	mv	a0,s2
	jalr	s1
	mv	s1,s10
	mv	s3,s0
	lw	s2,40(s3)
	mv	a0,s3
	jalr	s2
	mv	s2,s10
	j	L44
L45:	addi	zero,zero,0
	li	s10,1
	lw	s0,12(sp)
	lw	s1,8(sp)
	lw	s2,4(sp)
	lw	s3,0(sp)
	lw	ra,16(sp)
	addi	sp,sp,20
	jr	ra

	.text
	.global	LL_Start
LL_Start:
	addi	sp,sp,-36
	sw	ra,32(sp)
	sw	s0,28(sp)
	sw	s1,24(sp)
	sw	s2,20(sp)
	sw	s3,16(sp)
	sw	s4,12(sp)
	sw	s5,8(sp)
	sw	s6,4(sp)
	mv	s0,a0
	li	s10,52
	jal	_hallocate
	mv	s0,s10
	la	s1,List_Init
	sw	s1,12(s0)
	la	s1,List_InitNew
	sw	s1,16(s0)
	la	s1,List_Insert
	sw	s1,20(s0)
	la	s1,List_SetNext
	sw	s1,24(s0)
	la	s1,List_Delete
	sw	s1,28(s0)
	la	s1,List_Search
	sw	s1,32(s0)
	la	s1,List_GetEnd
	sw	s1,36(s0)
	la	s1,List_GetElem
	sw	s1,40(s0)
	la	s1,List_GetNext
	sw	s1,44(s0)
	la	s1,List_Print
	sw	s1,48(s0)
	mv	s0,s0
	mv	s2,s0
	lw	s1,12(s2)
	mv	a0,s2
	jalr	s1
	mv	s1,s10
	mv	s0,s0
	mv	s2,s0
	lw	s1,12(s2)
	mv	a0,s2
	jalr	s1
	mv	s1,s10
	mv	s2,s0
	lw	s1,48(s2)
	mv	a0,s2
	jalr	s1
	mv	s1,s10
	li	s10,36
	jal	_hallocate
	mv	s1,s10
	la	s2,Element_Init
	sw	s2,12(s1)
	la	s2,Element_GetAge
	sw	s2,16(s1)
	la	s2,Element_GetSalary
	sw	s2,20(s1)
	la	s2,Element_GetMarried
	sw	s2,24(s1)
	la	s2,Element_Equal
	sw	s2,28(s1)
	la	s2,Element_Compare
	sw	s2,32(s1)
	mv	s1,s1
	mv	s6,s1
	lw	s5,12(s6)
	li	s4,25
	li	s3,37000
	li	s2,0
	mv	a0,s6
	mv	a1,s4
	mv	a2,s3
	mv	a3,s2
	jalr	s5
	mv	s2,s10
	mv	s2,s0
	lw	s0,20(s2)
	mv	a0,s2
	mv	a1,s1
	jalr	s0
	mv	s0,s10
	mv	s2,s0
	lw	s1,48(s2)
	mv	a0,s2
	jalr	s1
	mv	s1,s10
	li	s10,10000000
	jal	_print
	li	s10,36
	jal	_hallocate
	mv	s1,s10
	la	s2,Element_Init
	sw	s2,12(s1)
	la	s2,Element_GetAge
	sw	s2,16(s1)
	la	s2,Element_GetSalary
	sw	s2,20(s1)
	la	s2,Element_GetMarried
	sw	s2,24(s1)
	la	s2,Element_Equal
	sw	s2,28(s1)
	la	s2,Element_Compare
	sw	s2,32(s1)
	mv	s1,s1
	mv	s6,s1
	lw	s5,12(s6)
	li	s4,39
	li	s3,42000
	li	s2,1
	mv	a0,s6
	mv	a1,s4
	mv	a2,s3
	mv	a3,s2
	jalr	s5
	mv	s2,s10
	mv	s10,s1
	sw	s10,0(sp)
	mv	s2,s0
	lw	s0,20(s2)
	mv	a0,s2
	mv	a1,s1
	jalr	s0
	mv	s0,s10
	mv	s2,s0
	lw	s1,48(s2)
	mv	a0,s2
	jalr	s1
	mv	s1,s10
	li	s10,10000000
	jal	_print
	li	s10,36
	jal	_hallocate
	mv	s1,s10
	la	s2,Element_Init
	sw	s2,12(s1)
	la	s2,Element_GetAge
	sw	s2,16(s1)
	la	s2,Element_GetSalary
	sw	s2,20(s1)
	la	s2,Element_GetMarried
	sw	s2,24(s1)
	la	s2,Element_Equal
	sw	s2,28(s1)
	la	s2,Element_Compare
	sw	s2,32(s1)
	mv	s1,s1
	mv	s6,s1
	lw	s5,12(s6)
	li	s4,22
	li	s3,34000
	li	s2,0
	mv	a0,s6
	mv	a1,s4
	mv	a2,s3
	mv	a3,s2
	jalr	s5
	mv	s2,s10
	mv	s2,s0
	lw	s0,20(s2)
	mv	a0,s2
	mv	a1,s1
	jalr	s0
	mv	s0,s10
	mv	s2,s0
	lw	s1,48(s2)
	mv	a0,s2
	jalr	s1
	mv	s1,s10
	li	s10,36
	jal	_hallocate
	mv	s1,s10
	la	s2,Element_Init
	sw	s2,12(s1)
	la	s2,Element_GetAge
	sw	s2,16(s1)
	la	s2,Element_GetSalary
	sw	s2,20(s1)
	la	s2,Element_GetMarried
	sw	s2,24(s1)
	la	s2,Element_Equal
	sw	s2,28(s1)
	la	s2,Element_Compare
	sw	s2,32(s1)
	mv	s1,s1
	mv	s6,s1
	lw	s5,12(s6)
	li	s4,27
	li	s3,34000
	li	s2,0
	mv	a0,s6
	mv	a1,s4
	mv	a2,s3
	mv	a3,s2
	jalr	s5
	mv	s2,s10
	mv	s3,s0
	lw	s2,32(s3)
	mv	a0,s3
	lw	s10,0(sp)
	mv	a1,s10
	jalr	s2
	mv	s2,s10
	mv	s10,s2
	jal	_print
	mv	s3,s0
	lw	s2,32(s3)
	mv	a0,s3
	mv	a1,s1
	jalr	s2
	mv	s1,s10
	mv	s10,s1
	jal	_print
	li	s10,10000000
	jal	_print
	li	s10,36
	jal	_hallocate
	mv	s1,s10
	la	s2,Element_Init
	sw	s2,12(s1)
	la	s2,Element_GetAge
	sw	s2,16(s1)
	la	s2,Element_GetSalary
	sw	s2,20(s1)
	la	s2,Element_GetMarried
	sw	s2,24(s1)
	la	s2,Element_Equal
	sw	s2,28(s1)
	la	s2,Element_Compare
	sw	s2,32(s1)
	mv	s1,s1
	mv	s6,s1
	lw	s5,12(s6)
	li	s4,28
	li	s3,35000
	li	s2,0
	mv	a0,s6
	mv	a1,s4
	mv	a2,s3
	mv	a3,s2
	jalr	s5
	mv	s2,s10
	mv	s2,s0
	lw	s0,20(s2)
	mv	a0,s2
	mv	a1,s1
	jalr	s0
	mv	s0,s10
	mv	s3,s0
	lw	s2,48(s3)
	mv	a0,s3
	jalr	s2
	mv	s2,s10
	li	s10,2220000
	jal	_print
	mv	s2,s0
	lw	s0,28(s2)
	mv	a0,s2
	lw	s10,0(sp)
	mv	a1,s10
	jalr	s0
	mv	s0,s10
	mv	s3,s0
	lw	s2,48(s3)
	mv	a0,s3
	jalr	s2
	mv	s2,s10
	li	s10,33300000
	jal	_print
	mv	s2,s0
	lw	s0,28(s2)
	mv	a0,s2
	mv	a1,s1
	jalr	s0
	mv	s0,s10
	mv	s1,s0
	lw	s0,48(s1)
	mv	a0,s1
	jalr	s0
	mv	s0,s10
	li	s10,44440000
	jal	_print
	li	s10,0
	lw	s0,28(sp)
	lw	s1,24(sp)
	lw	s2,20(sp)
	lw	s3,16(sp)
	lw	s4,12(sp)
	lw	s5,8(sp)
	lw	s6,4(sp)
	lw	ra,32(sp)
	addi	sp,sp,36
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

