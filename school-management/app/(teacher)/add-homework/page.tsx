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


export default function AddHomework() {
    const [date, setDate] = React.useState<Date>()

    const form = useForm<Student>({
        defaultValues: {
            firstName: '',
        },
        mode: "onChange",
    });

    const onSubmit = (data: Student) => {
        console.log('Submit', data);
    }
  return (
    <>
        <Form {...form}>
            <form onSubmit={form.handleSubmit(onSubmit)}>
                <div className="grid grid-cols-6 gap-4 py-4">
                    <div className="grid col-span-3 w-full max-w-sm items-center gap-1.5">
                        <FormField
                            control={form.control}
                            name='firstName'
                            render={({ field }) => (
                                <FormItem>
                                    <FormLabel htmlFor='name'>Name</FormLabel>
                                    <FormControl>
                                        <Input placeholder='Name' {...field} />
                                    </FormControl>
                                </FormItem>
                            )}
                        />
                            </div>
                            <div className="grid w-full max-w-sm items-center gap-1.5 col-span-3">
                                <Label htmlFor="name">Name</Label>
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
                    <Button type='submit'>Submit</Button>
            </form>
        </Form>
    </>
  )
}
