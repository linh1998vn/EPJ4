"use client"

import { DataTableRowActions } from "@/components/data-table/row-action"
import { Checkbox } from "@/components/ui/checkbox"
import { ColumnDef } from "@tanstack/react-table"

export type Course = {
  id: number
  name: String
  code: String
  courseDescription: String
  startDate: String
  endDate: String
}

export const columns: ColumnDef<Course>[] = [
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
    accessorKey: "code",
    header: "Code",
    cell: ({ row }) => <div className="w-[50px]">{row.getValue("code")}</div>
  },
  {
    accessorKey: "name",
    header: "Name",
  },
  {
    accessorKey: "  courseDescription",
    header: " Course Description",
  },  
  {
    accessorKey: " startDate",
    header: " Start Date",
  },
  {
    accessorKey: " End Date",
    header: " End Date",
  },
  {
    id: "actions",
    cell: ({ row }) => <DataTableRowActions row={row} />,
  },
]
