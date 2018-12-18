' esto es un comment
Function forfunc (a As Integer) As Integer
    a = 10
    If false Then
        forfunc(a)
    Else
        a = a - 1
        forfunc = a
    End If
End Function

Function eureka (b As Integer) As Integer
    b = 1 + 2
End Function

Sub Main()
    Dim sData As String 
    Dim sMessage As String
    Dim bool As Boolean
    Dim x As Integer
    bool = true
    Read x
    If bool OR false Then
        x = 9
    Else
        x = 1300
    End If

    If false AND bool Then
        x = 112
    Else
        x = 42
    End If
    x = 1
    x = forfunc(x)
    If x = 1 Then
        Dim sxMessage As String
        eureka(x)
    ElseIf x < 1 Then
        Dim hello As String
    Else
        Dim boole As Boolean
        For x = 1 To 50
            x = 2
            Print x
        Next
    End If
End Sub

Sub Hello (s As String)
    Dim number As Integer
    number = 0 * (2 + 5) / 8 - 1 
    Do 
    number = number + 1
    Loop While number < 201
    Dim sMessage As String
End Sub
