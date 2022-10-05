import { Table, TableBody, TableCell, TableHead, TableRow } from '@mui/material'
import React from 'react'
import { DataTableProps, DataRowType } from '../../../shared/types/interfaces'


const DataTable: React.FC<DataTableProps> = (props) => {
    return (
        <Table>
            <TableHead>
                {props.headerKeyTemplate.map(headerKeyPair =>
                    <TableCell>{headerKeyPair.headerName}</TableCell>)}
            </TableHead>
            <TableBody>
                {props.data.map((dataRow: DataRowType) => {
                    return (
                        <TableRow>
                            {props.headerKeyTemplate.map(keyHeaderPair =>
                                <TableCell>{dataRow[keyHeaderPair.key]}</TableCell>)}
                        </TableRow>)
                })
                }
            </TableBody>
        </Table>
    )
}

export default DataTable