'use client'

import { DataTable } from "@/components/data-table/data-table"
import { Student, columns } from "./columns"
import { Label } from "@/components/ui/label"
import { Input } from "@/components/ui/input"
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
import { Calendar as CalendarIcon } from "lucide-react"
import { format } from "date-fns"
import { Checkbox } from "@/components/ui/checkbox"
import { Textarea } from "@/components/ui/textarea"
import { useForm } from "react-hook-form"
import { Button } from '@/components/ui/button'
import {
    Select,
    SelectContent,
    SelectItem,
    SelectTrigger,
    SelectValue,
  } from "@/components/ui/select"
import { PlusCircle } from "lucide-react"
import {
    Dialog,
    DialogContent,
    DialogDescription,
    DialogFooter,
    DialogHeader,
    DialogTitle,
    DialogTrigger,
  } from "@/components/ui/dialog"
  import { getStudentList } from "@/services/student-api/student-api"

import * as React from "react"
import Link from "next/link"
import { getCourseList } from "@/services/course-api/course-api"


export default async function StudentList() {
    const [date, setDate] = React.useState<Date>()
    const data = await  getStudentList()
    const course = await getCourseList()
  return (
    <>
    <div className="grid grid-cols-8 gap-6">
      <div className='col-span-8 w-full'>
        <h1 className='text-3xl font-bold mt-4'>Student List</h1>
        <p className="mt-2 pb-7">List of students by class.</p>
      </div>
      <div className="col-span-8">
        <div className="border rounded-md p-4 grid grid-cols-12 gap-4">
            <div className="col-span-4 grid w-full items-center gap-1.5">
                <Label htmlFor="find-student">Find Student</Label>
                <Input type="text" id="find-student" placeholder="Student name ..." className="w-full"/>
            </div>
            <div className="col-span-2 grid w-full max-w-sm items-center gap-1.5">
            <Label htmlFor="course">Course</Label>
                <Select>
                    <SelectTrigger className="w-[179px]">
                    <SelectValue placeholder="Course" />
                    </SelectTrigger>
                    <SelectContent content="course" >
                   {course && course.map((item: any) => (
                      <>
                       <SelectItem value={item.id}>{item.name}</SelectItem>
                        </>
                    ))}
                    </SelectContent>
                  
                </Select>
            </div>
            <div className="col-span-2 grid w-full max-w-sm items-center gap-1.5">
                <Label htmlFor="class">Class</Label>
                <Select>
                    <SelectTrigger className="w-[179px]">
                    <SelectValue placeholder="Class" />
                    </SelectTrigger>
                    <SelectContent>
                        <SelectItem value="light">Light</SelectItem>
                        <SelectItem value="dark">Dark</SelectItem>
                        <SelectItem value="system">System</SelectItem>
                    </SelectContent>
                </Select>
            </div>
            <div className="col-span-2 grid w-full max-w-sm items-end gap-1.5">
                <Button>
                    <PlusCircle className="mr-2" /> Create team
                </Button>
            </div>
            
            <div className="col-span-2 grid w-full max-w-sm items-end gap-1.5">
            <Dialog>
      <DialogTrigger asChild>
        <Button>
            <PlusCircle className="mr-2" /> Homework
        </Button>
      </DialogTrigger>
      <DialogContent className="sm:max-w-[425px]">
        <DialogHeader>
          <DialogTitle>Create Homework</DialogTitle>
          <DialogDescription>
            Create team homework or personal homework for this class.
          </DialogDescription>
        </DialogHeader>
        <div className="grid grid-cols-6 gap-4 py-4">
                            <div className="grid col-span-3 w-full max-w-sm items-center gap-1.5">
                                <Label htmlFor="name">Name</Label>
                                <Input id="name" type="text" />
                            </div>
                            <div className="grid w-full max-w-sm items-center gap-1.5 col-span-3">
                                <Label htmlFor="date">Date</Label>
                                <Popover>
                                    <PopoverTrigger asChild>
                                        <Button
                                            variant={"outline"}
                                            className={cn(
                                                "w-full justify-start text-left font-normal",
                                                !date && "text-muted-foreground"
                                            )}
                                        >
                                            <CalendarIcon className="mr-2 h-4 w-4" />
                                            {date ? format(date, "PPP") : <span>Pick a date</span>}
                                        </Button>
                                    </PopoverTrigger>
                                    <PopoverContent className="w-auto p-0">
                                        <Calendar
                                            mode="single"
                                            selected={date}
                                            onSelect={setDate}
                                            initialFocus
                                        />
                                    </PopoverContent>
                                </Popover>
                            </div>
                            <div className="grid col-span-3 w-full max-w-sm items-center gap-1.5">
                                <Label htmlFor="picture">Picture</Label>
                                <Input id="picture" type="file" />
                            </div>
                            <div className="flex items-center space-x-2 col-span-3 mt-5">
                                <Checkbox id="team" />
                                <label
                                    htmlFor="team"
                                    className="text-sm font-medium leading-none peer-disabled:cursor-not-allowed peer-disabled:opacity-70"
                                >
                                    Team Homework
                                </label>
                            </div>
                            <div className="grid col-span-6 w-full items-center gap-1.5">
                                <Label htmlFor="description">Description</Label>
                                <Textarea placeholder="Description..." id="description" />
                            </div>
                        </div>
        <DialogFooter>
          <Button type="submit">Save</Button>
        </DialogFooter>
      </DialogContent>
    </Dialog>
            </div>         
        </div>
        </div>
        <div className="col-span-8">
        <DataTable columns={columns} data={data} />
       
      </div>
    </div>
    </>
  )
}
