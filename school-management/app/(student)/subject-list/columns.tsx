"use client"

import { DataTableRowActions } from "@/components/data-table/row-action"
import { Checkbox } from "@/components/ui/checkbox"
import { ColumnDef } from "@tanstack/react-table"
import { string } from "zod"

export type Subject = {
  id: number,
  subjectName: string
  subjectCode: string
  subjectDescription: string
  numberOfLessons: number
  delete: boolean
}

export const columns: ColumnDef<Subject>[] = [
  {
    id: "select",
    header: ({ table }) => (
      <Checkbox
        checked={table.getIsAllPageRowsSelected()}
        onCheckedChange={(value) => table.toggleAllPageRowsSelected(!!value)}
        aria-label="Select all"
        className="translate-y-[2px]"
      />
    ),
    cell: ({ row }) => (
      <Checkbox
        checked={row.getIsSelected()}
        onCheckedChange={(value) => row.toggleSelected(!!value)}
        aria-label="Select row"
        className="translate-y-[2px]"
      />
    )
  },
  {
    accessorKey: "id",
    header: "Id",
    cell: ({ row }) => <div className="w-[10px]">{row.getValue("id")}</div>
  },
  {
    accessorKey: "subjectName",
    header: "Subject Name",
    cell: ({ row }) => <div className="w-[50px]">{row.getValue("subjectName")}</div>
  },

  {
    accessorKey: "subjectCode",
    header: "Subject Code",
  },
  {
    accessorKey: "subjectDescription",
    header: "Subject Description",
  },
  {
    accessorKey: "numberOfLessons",
    header: "Number Of Lessons",
  },
  {
    accessorKey: "delete",
    header: "Delete",
  },
  {
    id: "actions",
    cell: ({ row }) => <DataTableRowActions row={row} />,
  },
]
