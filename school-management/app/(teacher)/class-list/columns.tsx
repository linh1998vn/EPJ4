"use client"

import { DataTableRowActions } from "@/components/data-table/row-action"
import { Checkbox } from "@/components/ui/checkbox"
import { ColumnDef } from "@tanstack/react-table"

export type Class = {
  id: number
  classroomName: string
  startTime: string
  endTime: string
  createdDate: string
  updatedDate: string
}

export const columns: ColumnDef<Class>[] = [
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
    accessorKey: "classroomName",
    header: "Classroom",
    cell: ({ row }) => <div className="w-[50px]">{row.getValue("classroomName")}</div>
  },
  {
    accessorKey: "startTime",
    header: "Start Time",
  },
  {
    accessorKey: " endTime",
    header: " End Time",
  },
  {
    accessorKey: "createdDate",
    header: "Created Date",
  },
  {
    accessorKey: "updatedDate",
    header: "Updated Date",
  },
  {
    id: "actions",
    cell: ({ row }) => <DataTableRowActions row={row} />,
  },
]
