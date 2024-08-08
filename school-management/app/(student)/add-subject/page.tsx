'use client'
import React from 'react'
import {
    Form,
    FormControl,
    FormDescription,
    FormField,
    FormItem,
    FormLabel,
    FormMessage,
} from "@/components/ui/form"
import { cn } from "@/lib/utils"
import { Calendar } from "@/components/ui/calendar"
import {
  Popover,
  PopoverContent,
  PopoverTrigger,
} from "@/components/ui/popover"
import { Calendar as CalendarIcon, PlusCircle } from "lucide-react"

import { format } from "date-fns"
import { Checkbox } from "@/components/ui/checkbox"
import { Textarea } from "@/components/ui/textarea"
import { useForm } from "react-hook-form"
import { Student } from '@/app/(teacher)/student-list/columns'
import { Input } from '@/components/ui/input'
import { Label } from '@/components/ui/label'
import { Button } from '@/components/ui/button'
import { Subject } from '../subject-list/columns'
import { CreateSubject } from '@/services/subject-api/subject-api'


export default function AddSubject() {
    const [date, setDate] = React.useState<Date>()
    const [subjectname, setsubjectname] = React.useState<string>('')
    const [subjectDescription, setsubjectDescription] = React.useState<string>('')
    const [subjectCode, setsubjectCode] = React.useState<string>('')
    const [numberOfLessons, setnumberOfLessons] = React.useState<number>()

    const [formLogin, setformLogin] = React.useState<any>({
        subjectName: subjectname,
        subjectDescription: subjectDescription,
        subjectCode:subjectCode,
        numberOfLessons:numberOfLessons
    });

    const form = useForm<Subject>({
    
        mode: "onChange",
    });
    
    const onSubmit = async () => {
        try {
            const responseLogin = await CreateSubject(formLogin)
            console.log(responseLogin)
         
        } catch (error) {
            alert("Tạo thất bại")
        }
    }
  return (
    <>
        <Form {...form}>
            <form onSubmit={form.handleSubmit(onSubmit)}>
                <div className="grid grid-cols-6 gap-4 py-4">
                    <div className="grid col-span-3 w-full max-w-sm items-center gap-1.5">
                        <FormField
                            control={form.control}
                            name='subjectName'
                            render={({ field }) => (
                                <FormItem>
                                    <FormLabel htmlFor='subjecName'>Name</FormLabel>
                                    
                                        <Input placeholder='subjecName' onChange={(e) => {setformLogin({ ...formLogin, subjectName: e.target.value})}} />
                                    
                                </FormItem>
                            )}
                        />

                        <FormField
                            control={form.control}
                            name='subjectDescription'
                            render={({ field }) => (
                                <FormItem>
                                    <FormLabel htmlFor='subjectDescription'>Description</FormLabel>
                                    <FormControl>
                                        <Input placeholder='subjectDescription' onChange={(e) => {setformLogin({ ...formLogin, subjectDescription:e.target.value})}} />
                                    </FormControl>
                                   
                                 

                                </FormItem>
                            )}
                            
                        />
                                <FormField
                            control={form.control}
                            name='subjectCode'
                            render={({ field }) => (
                                <FormItem>
                                    <FormLabel htmlFor='subjectCode'>Code</FormLabel>
                                    <FormControl>
                                        <Input placeholder='subjectCode' onChange={(e) => {setformLogin({ ...formLogin, subjectCode:e.target.value})}} />
                                    </FormControl>
                                </FormItem>
                            )}
                        />
                        <FormField
                            control={form.control}
                            name='numberOfLessons'
                            render={({ field }) => (
                                <FormItem>
                                    <FormLabel htmlFor='numberOfLessons'>Lesson Number</FormLabel>
                                    <FormItem>
                                        <Input placeholder='numberOfLessons' onChange={(e ) => {
                                             const valueAsNumber: number = parseInt(e.target.value)
                                             setformLogin({ ...formLogin, numberOfLessons: valueAsNumber})}} />
                                    </FormItem>

                                </FormItem>
                            )}
                        />

                            </div>
                            
                        </div>
                    <Button type='submit'>Submit</Button>
            </form>
        </Form>
    </>
  )
}
