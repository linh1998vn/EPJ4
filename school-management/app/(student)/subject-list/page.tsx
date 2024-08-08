'use client'

import { DataTable } from "@/components/data-table/data-table"
import { Subject, columns } from "./columns"
import { Label } from "@/components/ui/label"
import { Input } from "@/components/ui/input" 
import {
    Select,
    SelectContent,
    SelectItem,
    SelectTrigger,
    SelectValue,
  } from "@/components/ui/select"
import { getSubjectList } from "@/services/subject-api/subject-api"


export default async function SubjectList() {
  const data = await  getSubjectList()

  return (
    <>
    <div className="grid grid-cols-8 gap-6">
      <div className='col-span-8 w-full'>
        <h1 className='text-3xl font-bold mt-4'>Class List</h1>
        <p className="mt-2 pb-7">List of class by semester.</p>
      </div>
      <div className="col-span-8">
        <div className="border rounded-md p-4 grid grid-cols-12 gap-4">
            <div className="col-span-8 grid w-full items-center gap-1.5">
                <Label htmlFor="find-class">Find Class</Label>
                <Input type="text" id="find-class" placeholder="Class name ..." className="w-full"/>
            </div>
            <div className="col-span-2 grid w-full max-w-sm items-center gap-1.5">
                <Label htmlFor="course">Course</Label>
                <Select>
                    <SelectTrigger className="w-[179px]">
                    <SelectValue placeholder="Course" />
                    </SelectTrigger>
                    <SelectContent>
                        <SelectItem value="light">Light</SelectItem>
                        <SelectItem value="dark">Dark</SelectItem>
                        <SelectItem value="system">System</SelectItem>
                    </SelectContent>
                </Select>
            </div>
            <div className="col-span-2 grid w-full max-w-sm items-center gap-1.5">
                <Label htmlFor="semester">Semester</Label>
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
        </div>
        </div>
        <div className="col-span-8">
        <DataTable columns={columns} data={data} />
      </div>
    </div>
    </>
  )
}
